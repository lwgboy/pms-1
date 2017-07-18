package cn.yesway.pms.common.core.mybatis.interceptor;

import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import cn.yesway.pms.common.core.mybatis.dialect.Dialect;
import cn.yesway.pms.common.exception.DialectSetPropertiesException;

/**
 * Mybatis查询拦截器
 * 
 * @author XuePeng
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class ExecutorInterceptor extends AbstractInterceptor {

    private static final Logger LOGGER = Logger.getLogger(ExecutorInterceptor.class);
    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
    private static final int ROWBOUNDS_INDEX = 2;

    private static final String ORDER_BY = "order by";

    private Dialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    private void processIntercept(final Object[] queryArgs) {
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();
        // 分页
        if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = ms.getBoundSql(parameter);

            String sql = boundSql.getSql().replaceAll("\\s{2,}", " ").trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }

            limit = RowBounds.NO_ROW_LIMIT;

            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                    boundSql.getParameterObject());
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql), false);
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

            LOGGER.debug("==>" + sql);

        } else if (parameter instanceof CountParameter) {
            // 获取总数
            parameter = ((CountParameter) parameter).getParameter();
            BoundSql boundSql = ms.getBoundSql(parameter);

            String sql = boundSql.getSql().replaceAll("\\s{2,}", " ").replace(" FROM", " from")
                    .replace("ORDER BY", ORDER_BY).replace("GROUP BY", ORDER_BY).trim();

            if (sql.split("from").length > 2 || sql.split(ORDER_BY).length > 2 || sql.indexOf(ORDER_BY) > -1) {
                sql = "select count(1) from (" + sql + ") tmp";
            } else {
                int fromIndex = sql.indexOf(" from");
                sql = "select count(1)" + sql.substring(fromIndex);

                int orderByIndex = sql.indexOf(ORDER_BY);
                if (orderByIndex > 0) {
                    sql = sql.substring(0, orderByIndex);
                }
            }

            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                    boundSql.getParameterObject());
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql), true);
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
            queryArgs[PARAMETER_INDEX] = parameter;

            LOGGER.debug("==>" + sql);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new DialectSetPropertiesException(e, "000000001", "方言加载属性异常");
        }

    }

    public static class CountParameter {
        private Object parameter;

        public CountParameter(Object parameter) {
            this.parameter = parameter;
        }

        public Object getParameter() {
            return parameter;
        }
    }

}

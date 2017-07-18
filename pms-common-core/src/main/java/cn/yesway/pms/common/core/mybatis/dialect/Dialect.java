
package cn.yesway.pms.common.core.mybatis.dialect;

/**
 * 数据库方言接口。
 * 
 * @author XuePeng
 */
@FunctionalInterface
public interface Dialect {

    default boolean supportsLimit() {
        return false;
    }

    default boolean supportsLimitOffset() {
        return supportsLimit();
    }

    /**
     * 将sql变成分页sql语句，直接使用offset-limit的值作为占位符。
     */
    default String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
    }

    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换。
     */
    String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder);
}

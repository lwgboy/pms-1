package cn.yesway.pms.common.core.mybatis.dialect;

/**
 * MySQL数据库分页适配器。
 * 
 * @author XuePeng
 */
public class MySqlDialect implements Dialect {

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit,
            String limitPlaceholder) {
        StringBuilder stringBuilder = new StringBuilder(sql);

        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offsetPlaceholder);
            stringBuilder.append(",");
        }
        stringBuilder.append(limitPlaceholder);
        return stringBuilder.toString();
    }
}

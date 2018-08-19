package org.cx.ds;

/**
 * @author grass
 * @date 2018/8/11
 */
public class DynamicDataSourceHolder {

    private static ThreadLocal<DataSourceEnum> holder = new ThreadLocal<DataSourceEnum>();

    private DynamicDataSourceHolder() {}

    public static DataSourceEnum getDataSource() {
        return holder.get();
    }

    public static void putDataSource(DataSourceEnum dataSourceEnum) {
        holder.set(dataSourceEnum);
    }

    public static void clearDataSource() {
        holder.remove();
    }
}

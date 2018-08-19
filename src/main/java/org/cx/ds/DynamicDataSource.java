package org.cx.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author grass
 * @date 2018/8/11
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private DataSource masterDataSource; //写数据源
    private List<DataSource> slavesDataSource = new ArrayList<>(); //读数据源

    @Override
    public void afterPropertiesSet() {
        if (this.masterDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(masterDataSource);

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DataSourceEnum.WRITE.name(), masterDataSource);

        if (slavesDataSource != null && slavesDataSource.size() > 0) {
            for (int i = 0; i < slavesDataSource.size(); i++) {
                targetDataSources.put(DataSourceEnum.READ.name(), slavesDataSource.get(i));
            }
        }

        // 设置数据源
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceEnum  dataSourceEnum = DynamicDataSourceHolder.getDataSource();
        if (dataSourceEnum == null || dataSourceEnum == DataSourceEnum.WRITE) {
            return DataSourceEnum.WRITE.name();
        }
        return DataSourceEnum.READ.name();
    }

    public DataSource getMasterDataSource() {
        return masterDataSource;
    }

    public void setMasterDataSource(DataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    public List<DataSource> getSlavesDataSource() {
        return slavesDataSource;
    }

    public void setSlavesDataSource(List<DataSource> slavesDataSource) {
        this.slavesDataSource = slavesDataSource;
    }
}

package org.cx.ds;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author grass
 * @date 2018/8/11
 */

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class DynamicPlugin implements Interceptor {

    private static final Map<String, DataSourceEnum> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean syncActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (!syncActive) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            DataSourceEnum dataSourceEnum = null;
            dataSourceEnum = cacheMap.get(mappedStatement.getId());
            if (dataSourceEnum == null) {
                if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    dataSourceEnum = DataSourceEnum.READ;
                } else {
                    dataSourceEnum = DataSourceEnum.WRITE;
                }
                cacheMap.put(mappedStatement.getId(), dataSourceEnum);
            }

            DynamicDataSourceHolder.putDataSource(dataSourceEnum);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

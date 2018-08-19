package org.cx.ds;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * @author grass
 * @date 2018/8/11
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {


    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {

        boolean readOnly = definition.isReadOnly();
        if (readOnly) {
            DynamicDataSourceHolder.putDataSource(DataSourceEnum.READ);
        } else {
            DynamicDataSourceHolder.putDataSource(DataSourceEnum.WRITE);
        }
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}

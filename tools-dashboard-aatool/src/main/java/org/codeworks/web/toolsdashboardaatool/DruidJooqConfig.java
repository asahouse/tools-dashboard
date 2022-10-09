package org.codeworks.web.toolsdashboardaatool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.vendor.MySqlValidConnectionChecker;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * Created by benjaminkc on 17/7/23.
 */
@Configuration
public class DruidJooqConfig {

    @Bean
    public DSLContext dsl(){
        return getDSLContext();
    }

    @Value("${spring.datasource.driver-class-name}")
    String classname;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    private DSLContext getDSLContext() {
        DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(classname);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setMaxActive(15000);
            dataSource.setMaxWait(86_400);
            dataSource.setMinIdle(0);
            dataSource.setTestOnBorrow(true);
            //指明连接是否被空闲连接回收器(如果有)进行检验，如果检测失败，则连接将被从池中去除
            dataSource.setTestWhileIdle(true);
            dataSource.setInitialSize(20);
            //连接池中连接，在时间段内一直空闲，被逐出连接池的时间(1000*60*60)，以毫秒为单位
            dataSource.setMinEvictableIdleTimeMillis(1000*60*60);
            //在空闲连接回收器线程运行期间休眠的时间值,以毫秒为单位，一般比minEvictableIdleTimeMillis小
            dataSource.setTimeBetweenEvictionRunsMillis(60*1000);
            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(3500);
            dataSource.setValidConnectionChecker(new MySqlValidConnectionChecker());

        //Spring托管事务方式
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        DataSourceTransactionManager txMgr =  new DataSourceTransactionManager(dataSource);

        org.jooq.Configuration configuration =
                new DefaultConfiguration()
                .set(new DataSourceConnectionProvider(proxy))
                .set(new SpringTransactionProvider(txMgr))
                .set(SQLDialect.MYSQL);


        return DSL.using(configuration);
    }

}

package org.example.payment.zhifubao.config;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.itbaizhan.mapper1",sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MyBaitsConfig1 {

    @Bean(name = "masterDataSource")
    public DataSource getDataSource(DBConfig1 db){
        // 1. 将本地事务注册到 Atomikos全局事务中
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        // 2. 设置rm供应商
        sourceBean.setUniqueResourceName("masterDataSource");
        sourceBean.setXaDataSourceClassName(db.getDriverClassName());
        // 3. 设置testquery
        sourceBean.setTestQuery("select 1");
        // 4. 设置超时时间
        sourceBean.setBorrowConnectionTimeout(3);
        // 设置Mysql链接
        MysqlXADataSource dataSource = new MysqlXADataSource();
        dataSource.setUrl(db.getUrl());
        dataSource.setUser(db.getUsername());
        dataSource.setPassword(db.getPassword());
        sourceBean.setXaDataSource(dataSource);

        return sourceBean;
    }


    /**
     * SqlSessionFactory是mybaits 重要的对象
     * @return
     */
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }


    /**
     * 负责管理mybatis 的sqlsession sql
     * SqlSessionTemplate 替换默认的mybaits 实现的defalutsqlsession不能参与spring事务不能注入 线程不安全
     * @return
     */
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}

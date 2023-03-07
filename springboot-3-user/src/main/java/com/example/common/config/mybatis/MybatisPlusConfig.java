package com.example.common.config.mybatis;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.example.common.config.mybatis.plugins.CustomInterceptor;
import com.example.common.config.mybatis.plugins.PrintSqlInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.**.mapper*")
public class MybatisPlusConfig {
    /**
     * 分页插件 3.5.X
     * @author zhengkai.blog.csdn.net
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setMaxLimit(-1L);
        paginationInterceptor.setDbType(DbType.MYSQL);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setOptimizeJoin(true);
        return paginationInterceptor;
    }

    /******Mybatis插件执行顺序 按Bean加载顺序从后往前 *******/

    /**
     * MybatisPlus插件 这东西得放在最后执行，因为它不传递责任链
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //注册分页插件
        mybatisPlusInterceptor.setInterceptors(Collections.singletonList(paginationInnerInterceptor()));
        return mybatisPlusInterceptor;
    }

    /**
     * 注册逻辑字段插件
     * update_time、update_id、create_time、create_id
     * data_version、is_del、tanent_id
     */
//    @Bean
    public CustomInterceptor customInterceptor(){
        return new CustomInterceptor();
    }

    /**
     * 注册sql日志插件
     * 这里无法得到mybatis plus处理后的sql，一般不在这里获取。直接使用mybatis输出的sql日志
     */
//    @Bean
    public PrintSqlInterceptor printSqlInterceptor(){
        return new PrintSqlInterceptor();
    }
}

package com.example.config.mybatis.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class TenantInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动插入tenant_id
            Field fieldCreate = object.getClass().getDeclaredField("tenant_id");
            fieldCreate.setAccessible(true);
            fieldCreate.set(object, "get tenant_id");
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //update时，自动更新tenant_id
                Field fieldUpdate = object.getClass().getDeclaredField("tenant_id");
                fieldUpdate.setAccessible(true);
                fieldUpdate.set(object, "get tenant_id");
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
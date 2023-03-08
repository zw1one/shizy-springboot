package mq.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MpGenerator {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&zeroDateTimeBehavior=convertToNull",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("shizy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_") // 设置过滤表前缀

                            .entityBuilder()
                            .enableChainModel()//开启链式模型
                            .enableLombok()//开启 lombok 模型
                            .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解

                            .controllerBuilder().enableRestStyle()//开启生成@RestController 控制器

                            .mapperBuilder()
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .enableBaseColumnList()//启用 BaseColumnList

                    .build();
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
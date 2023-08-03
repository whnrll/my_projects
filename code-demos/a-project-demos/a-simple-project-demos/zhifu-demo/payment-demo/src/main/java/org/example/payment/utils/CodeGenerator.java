package org.example.payment.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * 代码生成工作
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/payment", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("itbaizhan") // 设置作者
                            .commentDate("MM-dd") // 注释日期格式 C:\Users\wangc\IdeaProjects\payment-demo2\src\main\java
                            .outputDir(System.getProperty("user.dir")+ "/src/main/java/") // 指定输出目录
                            .fileOverride(); //覆盖文件
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.itbaizhan") // 包名前缀
                            .entity("entity") //实体类包名
                            .mapper("mapper") //mapper接口包名
                            .service("service"); //service包名
                })

                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    List<String> strings = Arrays.asList("order_info", "payment_info", "refund_info");
                    builder.addInclude(strings)
                            // 开始实体类配置
                            .entityBuilder()
                            // 开启lombok模型
                            .enableLombok()
                            //表名下划线转驼峰
                            .naming(NamingStrategy.underline_to_camel)
                            //列名下划线转驼峰
                            .columnNaming(NamingStrategy.underline_to_camel);
                })
                .execute();
    }
}

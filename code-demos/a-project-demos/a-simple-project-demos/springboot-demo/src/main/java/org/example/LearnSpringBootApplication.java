package org.example;

import org.example.listener.FilterFileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LearnSpringBootApplication {
    private static FilterFileConfig filterFileConfig;

    @Autowired
    public void setFilterFileConfig(FilterFileConfig filterFileConfig) {
        LearnSpringBootApplication.filterFileConfig = filterFileConfig;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LearnSpringBootApplication.class, args);

        filterFileConfig.fileFilter("D:\\github-workspace\\learn-springcloud-alibaba\\learn-springboot\\tmp");
    }

}

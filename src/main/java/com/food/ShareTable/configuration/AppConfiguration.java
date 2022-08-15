package com.food.ShareTable.configuration;

import com.food.ShareTable.Common.CommonConstant;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.logging.Logger;

@Configuration
public class AppConfiguration {

    @Bean
    public Log4jLogger shareTable_debug_log(){
        return (Log4jLogger) LoggerFactory.getLogger(CommonConstant.debugLogger);
    }

}

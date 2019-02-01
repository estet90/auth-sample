package ru.kononov.authsample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogFormatter;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.Logbook;

@Configuration
public class LogbookConfiguration {

    private final Logger logger = LoggerFactory.getLogger("ru.kononov.authsample.ApiLogger");

    @Bean
    Logbook logbook() {
        return Logbook.builder()
                .formatter(new DefaultHttpLogFormatter())
                .writer(new DefaultHttpLogWriter(logger))
                .build();
    }

}

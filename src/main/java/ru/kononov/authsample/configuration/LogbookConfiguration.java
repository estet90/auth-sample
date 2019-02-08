package ru.kononov.authsample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.util.Objects.isNull;
import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;

@Configuration
public class LogbookConfiguration {

    @Bean
    Logbook logbook() {
        return Logbook.builder()
                .formatter(new DefaultHttpLogFormatter())
                .writer(new ApiLogWriter())
                .bodyFilter(new ApiBodyFilter())
                .condition(exclude(
                        requestTo("**/css/**"),
                        requestTo("**/js/**"),
                        requestTo("**/jquery/**")
                ))
                .build();
    }

    private static class ApiLogWriter implements HttpLogWriter {

        private final Logger logger = LoggerFactory.getLogger("ru.kononov.authsample.ApiLogger");

        @Override
        public void writeRequest(@Nonnull Precorrelation<String> precorrelation) {
            MDC.put("requestId", precorrelation.getId());
            logger.trace(precorrelation.getRequest());
        }

        @Override
        public void writeResponse(@Nonnull Correlation<String, String> correlation) {
            logger.trace(correlation.getResponse());
            MDC.clear();
        }

    }

    private static class ApiBodyFilter implements BodyFilter {

        @Override
        public String filter(@Nullable String contentType, @Nonnull String body) {
            return isNull(contentType) || contentType.contains("html") ? "html content with size " + body.length() : body;
        }

    }

}

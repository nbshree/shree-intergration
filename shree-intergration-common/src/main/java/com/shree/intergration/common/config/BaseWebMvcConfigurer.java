package com.shree.intergration.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shree.intergration.common.base.lang.DefaultDateFormat;
import com.shree.intergration.common.util.JacksonUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class BaseWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper() {
        return JacksonUtils.objectMapperConfigure(null);
    }

    @Bean
    public Formatter<Date> dateFormatter() {
        return new Formatter<Date>() {
            @Override
            public Date parse(String text, Locale locale) {
                Date date = null;
                try {
                    date = DefaultDateFormat.DATE_SIMPLE_FORMAT.parse(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }

            @Override
            public String print(Date object, Locale locale) {
                return DefaultDateFormat.DATE_SIMPLE_FORMAT.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text, DefaultDateFormat.DATE_FORMATTER);
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return DefaultDateFormat.DATE_FORMATTER.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text, DefaultDateFormat.DATE_TIME_FORMATTER);
            }

            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return DefaultDateFormat.DATE_TIME_FORMATTER.format(localDateTime);
            }
        };
    }

    @Bean
    public Converter<String, Date> dateConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                Date date = null;
                try {
                    date = DefaultDateFormat.DATE_SIMPLE_FORMAT.parse(source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> localDateConvert() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                LocalDate date = null;
                try {
                    date = LocalDate.parse(source, DefaultDateFormat.DATE_FORMATTER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                LocalDateTime date = null;
                try {
                    date = LocalDateTime.parse(source, DefaultDateFormat.DATE_TIME_FORMATTER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}

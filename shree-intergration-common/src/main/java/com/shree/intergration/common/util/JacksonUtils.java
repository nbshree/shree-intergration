package com.shree.intergration.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.shree.intergration.common.base.lang.DefaultDateFormat;
import com.shree.intergration.common.base.singleton.AbstractSingleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

/**
 * Jackson便捷工具类
 *
 * @author Darkprayer
 */
@Log4j2
public class JacksonUtils {

    private static final String ERROR_LOG = "JacksonUtils执行%s命令时发生错误！";

    /**
     * JsonMapper单例对象
     */
    public static final AbstractSingleton<ObjectMapper> OBJ_MAPPER_HOLDER = new AbstractSingleton<ObjectMapper>() {
        @Override
        protected ObjectMapper newInstance() {
            return objectMapperConfigure(null);
        }
    };

    /**
     * XmlMapper单例对象
     */
    public static final AbstractSingleton<XmlMapper> XML_MAPPER_HOLDER = new AbstractSingleton<XmlMapper>() {
        @Override
        protected XmlMapper newInstance() {
            return xmlMapperConfigure(null);
        }
    };

    public static ObjectMapper getJsonMapper() {
        return OBJ_MAPPER_HOLDER.getInstance();
    }

    public static XmlMapper getXmlMapper() {
        return XML_MAPPER_HOLDER.getInstance();
    }

    /**
     * 创建Jackson的JSON Mapper自定义配置
     *
     * @param objectMapper 已有的ObjectMapper，如果为空则创建个新的ObjectMapper
     * @return 自定义配置的ObjectMapper
     */
    public static ObjectMapper objectMapperConfigure(ObjectMapper objectMapper) {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
        }
        // 注册JDK1.8新类型
        objectMapper.registerModule(new Jdk8Module());
        // 注册JDK1.8新类型和自定义格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DefaultDateFormat.DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DefaultDateFormat.DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DefaultDateFormat.TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DefaultDateFormat.DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DefaultDateFormat.DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DefaultDateFormat.TIME_FORMATTER));
        objectMapper.registerModule(javaTimeModule);
        // 大数字直接序列化为 String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        // 大浮点数直接序列化为 String
        simpleModule.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
        });
        // 注册数字处理Module
        objectMapper.registerModule(simpleModule);
        // Mapper其他配置
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 普通Date类型时间序列化格式设置
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        objectMapper.setDateFormat(DefaultDateFormat.DATE_TIME_SIMPLE_FORMAT);
        // 时区设定
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return objectMapper;
    }

    /**
     * 创建Jackson的XML Mapper自定义配置
     *
     * @param xmlMapper 已有的XmlMapper，如果为空则创建个新的XmlMapper
     * @return 自定义配置的XmlMapper
     */
    public static XmlMapper xmlMapperConfigure(XmlMapper xmlMapper) {
        if (null == xmlMapper) {
            xmlMapper = new XmlMapper();
        }
        xmlMapper.findAndRegisterModules();
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper;
    }

    /**
     * 将实体对象转换为JSON字符串
     *
     * @param obj 实体对象
     * @return JSON字符串
     */
    public static String getJson(Object obj) {
        String resultText;
        try {
            resultText = getJsonMapper().writeValueAsString(obj);
            return resultText;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "getJson"), ex);
            return null;
        }
    }

    /**
     * 把JSON字符串转换为相应的实体对象
     *
     * @param jsonString JSON字符串
     * @param valueType  对象类型
     * @param <T>        对象类型泛型
     * @return 实体对象
     */
    public static <T> T parseJson(String jsonString, Class<T> valueType) {
        T obj;
        try {
            obj = getJsonMapper().readValue(jsonString, valueType);
            return obj;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "parseJson"), ex);
            return null;
        }
    }

    /**
     * 把JSON字符串转换为相应的实体对象List
     *
     * @param jsonString   JSON字符串
     * @param valueTypeRef 值引用类型
     * @param <T>          对象类型泛型
     * @return 实体对象List
     * @throws Exception 序列化错误
     */
    public static <T> T parseJson(String jsonString, TypeReference<T> valueTypeRef) {
        T obj;
        try {
            obj = getJsonMapper().readValue(jsonString, valueTypeRef);
            return obj;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "parseJson"), ex);
            return null;
        }
    }

    /**
     * 将实体对象转换为XML字符串
     *
     * @param obj 实体对象
     * @return XML字符串
     */
    public static String getXml(Object obj) {
        String resultText;
        try {
            resultText = getXmlMapper().writeValueAsString(obj);
            return resultText;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "getXml"), ex);
            return null;
        }
    }

    /**
     * 把XML字符串转换为相应的实体对象
     *
     * @param xmlString XML字符串
     * @param valueType 对象类型
     * @param <T>       对象类型泛型
     * @return 实体对象
     */
    public static <T> T parseXml(String xmlString, Class<T> valueType) {
        T obj;
        try {
            obj = getXmlMapper().readValue(xmlString, valueType);
            return obj;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "parseXml"), ex);
            return null;
        }
    }

    /**
     * 把XML字符串转换为相应的实体对象List
     *
     * @param xmlString    XML字符串
     * @param valueTypeRef 值引用类型
     * @param <T>          对象类型泛型
     * @return 实体对象List
     */
    public static <T> T parseXml(String xmlString, TypeReference<T> valueTypeRef) {
        T obj;
        try {
            obj = getXmlMapper().readValue(xmlString, valueTypeRef);
            return obj;
        } catch (Exception ex) {
            log.error(String.format(ERROR_LOG, "parseXml"), ex);
            return null;
        }
    }
}

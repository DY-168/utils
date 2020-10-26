package com.quantumfluctuation.util;

import com.alibaba.fastjson.JSONWriter;

import java.lang.reflect.Field;
import java.util.Collection;

public class FastJsonUtils {

    public static void writeObjectAllKeyAndValue(JSONWriter writer, Object o) {
        for (Field field : o.getClass().getDeclaredFields()) {
            Object fieldValue = ReflectionUtils.getFieldValue(o, field.getName());
            if (fieldValue != null) {
                writer.writeKey(field.getName());
                writer.writeValue(fieldValue);
            }
        }
    }

    public static void writeArray(JSONWriter writer, Object... array) {
        writer.startArray();
        for (Object o : array) {
            writer.writeValue(o);
        }
        writer.endArray();
    }

    public static void writeCollection(JSONWriter writer, Collection collection) {
        writer.startArray();
        for (Object o : collection) {
            writer.writeValue(o);
        }
        writer.endArray();
    }
}

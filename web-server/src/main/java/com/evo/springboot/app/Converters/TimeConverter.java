package com.evo.springboot.app.Converters;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime convert(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String convert(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}

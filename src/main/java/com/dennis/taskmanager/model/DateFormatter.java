package com.dennis.taskmanager.model;

import com.dennis.taskmanager.db.LocalDatabase;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

public class DateFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return null;
    }
}

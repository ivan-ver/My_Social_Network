package com.MSN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Application.class, args);
    }
}

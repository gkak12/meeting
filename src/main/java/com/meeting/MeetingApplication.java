package com.meeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.meeting")
public class MeetingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetingApplication.class, args);
    }
}
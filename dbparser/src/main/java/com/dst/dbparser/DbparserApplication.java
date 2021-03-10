package com.dst.dbparser;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DbparserApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DbparserApplication.class, args);

        DbParser dbCloner = new DbParser();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        long delay = 1L;
        long period = 60L;
        executorService.scheduleWithFixedDelay(dbCloner, delay, period, TimeUnit.SECONDS);
    }
}
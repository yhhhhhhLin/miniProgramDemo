package shop.linyh.miniProgramDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean("taskSendNotifyExecutor")
    public Executor taskSendNotifyExecutor() {

        int corePoolSize = 10;
        int maxPoolSize = 20;
        int queueCapacity = 100;

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                new java.util.concurrent.LinkedBlockingQueue<>(queueCapacity),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }
}

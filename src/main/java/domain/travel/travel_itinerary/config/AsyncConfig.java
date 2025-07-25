package domain.travel.travel_itinerary.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

    @Bean(name = "uploadPhotoExecutor")
    public Executor uploadPhotoExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("upload-thread-");
        executor.initialize();

        executor.setRejectedExecutionHandler((r, exec) -> {
            log.warn("Task rejected due to overload: {}", r.toString());
        });

        return executor;
    }

    @Bean(name = "deletePhotoExecutor")
    public Executor deletePhotoExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("delete-thread-");
        executor.initialize();

        executor.setRejectedExecutionHandler((r, exec) -> {
            log.warn("Task rejected due to overload: {}", r.toString());
        });

        return executor;
    }
}

package tobyspring.study;

import java.lang.annotation.Retention;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyTest {

    @Test
    public void testConcurrencyIssue() throws InterruptedException {
        // 스레드 수 및 각 스레드에서 실행할 증분 횟수 설정
        int numberOfThreads = 100;
        int incrementsPerThread = 1000;

        // MyConfig 클래스를 사용하여 스프링 컨테이너를 생성하고 Bean 인스턴스 가져오기
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ConcurrencyBean bean = context.getBean(ConcurrencyBean.class);

        // 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        // 카운트 다운 래치 초기화 (모든 스레드가 완료되면 래치를 해제)
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // 각 스레드에서 incrementCount() 호출
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    bean.incrementCount();
                }
                latch.countDown();
            });
        }

        latch.await();
        executorService.shutdown();
        assertEquals(numberOfThreads * incrementsPerThread , bean.getCount());
    }

    @Configuration
    static class MyConfig {
        @Bean
        @Scope(value = "prototype")
        ConcurrencyBean bean() {
            return new ConcurrencyBean();
        }
    }



    static class ConcurrencyBean {
        private int count;

        public void  incrementCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

    }
}

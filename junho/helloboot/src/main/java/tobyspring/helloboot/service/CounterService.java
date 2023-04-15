package tobyspring.helloboot.service;


import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int count = 0;

    public synchronized int incrementAndGet() {
        count++;
        return count;
    }
}

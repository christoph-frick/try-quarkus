package net.ofnir.quarkus;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CounterService {

    AtomicLong counter = new AtomicLong();

    public Long get() {
        return counter.get();
    }

    public Long inc() {
        return counter.incrementAndGet();
    }

}

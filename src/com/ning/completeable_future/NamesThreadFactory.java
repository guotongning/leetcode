package com.ning.completeable_future;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamesThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadGroup threadGroup;

    public NamesThreadFactory(String namePrefix) {
        SecurityManager manager = System.getSecurityManager();
        threadGroup = manager != null ? manager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        if (namePrefix == null || namePrefix.isEmpty()) {
            namePrefix = "default";
        }
        this.namePrefix = namePrefix + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, namePrefix + threadNumber.incrementAndGet(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}

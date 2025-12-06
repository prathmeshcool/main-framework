package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigReader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int maxRetry = ConfigReader.getInt("retryCount", 2);

    // Keyed by methodQualifiedName@threadId to be safe in parallel runs
    private static final ConcurrentHashMap<String, AtomicInteger> attempts = new ConcurrentHashMap<>();

    @Override
    public boolean retry(ITestResult result) {
        String key = result.getMethod().getQualifiedName() + "@" + Thread.currentThread().getId();
        AtomicInteger count = attempts.computeIfAbsent(key, k -> new AtomicInteger(0));

        int current = count.getAndIncrement(); // returns previous value
        if (current < maxRetry) {
            System.out.println("Retrying " + result.getMethod().getMethodName() + " (attempt " + (current + 2) + ")");
            return true;
        }
        return false;
    }
}

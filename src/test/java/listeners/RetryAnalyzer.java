package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int maxRetry = ConfigReader.getInt("retryCount", 2);
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            System.out.println("Retrying " + result.getMethod().getMethodName() + " : attempt " + (count+1));
            return true;
        }
        return false;
    }
}

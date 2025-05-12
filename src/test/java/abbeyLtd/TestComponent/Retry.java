package abbeyLtd.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    /**
     * Returns true if the test method has to be retried, false otherwise.
     *
     * @param result The result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */

    int count = 0;
    int retryLimit = 1;
    @Override
    public boolean retry(ITestResult result) {
        // to treat flaky tests
        if(count < retryLimit) {
            count++;
            return true;
        }
        return false;
    }
}

/*What Happens in Real Time:
First time test fails
    → count = 0
        → 0 < 1 is true
        → Retry the test, and increase count to 1.
Test fails again
    → count = 1
        → 1 < 1 is false
        → Do not retry anymore.
        🧾 Summary:
        (count < retryLimit) = “Have I hit the retry limit yet?”
        If not, retry.
        If yes, stop retrying.*/

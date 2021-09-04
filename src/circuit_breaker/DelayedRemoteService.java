package circuit_breaker;

public class DelayedRemoteService implements RemoteService {
    long serverStartTime;
    int delay;

    public DelayedRemoteService(long serverStartTime, int delay) {
        this.serverStartTime = serverStartTime;
        this.delay = delay;
    }

    public DelayedRemoteService() {
        this.serverStartTime = System.nanoTime();
        this.delay = 20;
    }

    @Override
    public String call() throws Exception {
        long currentTime = System.nanoTime();
        if ((currentTime - serverStartTime) * 1.0 / (1000 * 1000 * 1000) < delay) {
            throw new Exception("delayed service is down");
        }
        return "delayed service is working";
    }
}

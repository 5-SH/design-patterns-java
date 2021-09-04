package circuit_breaker;

public class QuickRemoteService implements RemoteService {
    @Override
    public String call() throws Exception {
        return "quick service is working";
    }
}

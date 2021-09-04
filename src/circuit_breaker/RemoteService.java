package circuit_breaker;

public interface RemoteService {
    String call() throws Exception;
}

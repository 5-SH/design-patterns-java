package circuit_breaker;

public interface CircuitBreaker {
    String attemptRequest() throws Exception;
    String getState();
    void recordFailure(String response);
    void recordSuccess();
    void setState(State state);
}

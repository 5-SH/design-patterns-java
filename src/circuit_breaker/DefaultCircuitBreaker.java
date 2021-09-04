package circuit_breaker;

public class DefaultCircuitBreaker implements CircuitBreaker {
    private long timeout;
    private long retryTimePeriod;
    private RemoteService service;
    long lastFailureTime;
    private String lastFailureResponse;
    int failureCount;
    private int failureThreshold;
    private State state;
    private long futureTime = 1000 * 1000 * 1000 * 1000;

    public DefaultCircuitBreaker(RemoteService serviceToCall, long timeout, int failureThreshold, long retryTimePeriod) {
        this.service = serviceToCall;
        this.state = State.CLOSED;
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.retryTimePeriod = retryTimePeriod;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.failureCount = 0;
    }

    @Override
    public String attemptRequest() throws Exception {
        evaluateState();
        if (state == State.OPEN) {
            return this.lastFailureResponse;
        } else {
            // API request
            try {
                String response = service.call();
                recordSuccess();
                return response;
            } catch (Exception e) {
                recordFailure(e.getMessage());
                throw e;
            }
        }
    }


    @Override
    public void recordFailure(String response) {
        failureCount += 1;
        this.lastFailureTime = System.nanoTime();
        this.lastFailureResponse = response;
    }

    @Override
    public void recordSuccess() {
        this.failureCount = 0;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.state = State.CLOSED;
    }

    @Override
    public String getState() {
        evaluateState();
        return state.name();
    }

    @Override
    public void setState(State state) {
        this.state = state;
        switch (state) {
            case OPEN:
                this.failureCount = failureThreshold;
                this.lastFailureTime = System.nanoTime();
                break;
            case HALF_OPEN:
                this.failureCount = failureThreshold;
                this.lastFailureTime = System.nanoTime() - retryTimePeriod;
                break;
            default:
                this.failureCount = 0;
        }
    }

    protected void evaluateState() {
        if (failureCount >= failureThreshold) {
            if ((System.nanoTime() - lastFailureTime) > retryTimePeriod) {
                state = State.HALF_OPEN;
            } else {
                state = State.OPEN;
            }
        } else {
            state = State.CLOSED;
        }
    }
}

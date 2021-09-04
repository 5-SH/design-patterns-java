package circuit_breaker;

public class MonitoringService {
    CircuitBreaker delayedService;
    CircuitBreaker quickService;

    public MonitoringService(CircuitBreaker delayedService, CircuitBreaker quickService) {
        this.delayedService = delayedService;
        this.quickService = quickService;
    }

    public String localResourceResponse() {
        return "local service is working";
    }

    public String delayedServiceResponse() {
        try {
            return this.delayedService.attemptRequest();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String quickServiceResponse() {
        try {
            return this.quickService.attemptRequest();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

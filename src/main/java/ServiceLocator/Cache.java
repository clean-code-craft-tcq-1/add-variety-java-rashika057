package ServiceLocator;

import java.util.ArrayList;
import java.util.List;

import AlertTarget.IAlertTargetService;

public class Cache {
    private List<IAlertTargetService> services = new ArrayList<>();

    public IAlertTargetService getService(String serviceName) {
    	return services.stream().filter(service -> service.getClass().getName().equalsIgnoreCase(serviceName)).findFirst().orElse(null);
    }

    public void addService(IAlertTargetService newService) {
    	services.add(newService);
    }
}

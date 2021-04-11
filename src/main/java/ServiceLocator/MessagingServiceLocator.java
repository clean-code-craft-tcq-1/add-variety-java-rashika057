package ServiceLocator;

import AlertTarget.IAlertTargetService;

public class MessagingServiceLocator implements IServiceLocator {

    private static Cache cache = new Cache();

    public IAlertTargetService getService(String targetName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	String serviceName = targetName + "Service";
    	IAlertTargetService service = cache.getService(serviceName);

        if (service != null) {
            return service;
        }

        InitialContext context = new InitialContext();
        IAlertTargetService service1 = context
          .lookup(serviceName);
        cache.addService(service1);
        return service1;
    }

}

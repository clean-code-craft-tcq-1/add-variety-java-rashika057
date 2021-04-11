package ServiceLocator;

import AlertTarget.IAlertTargetService;

public class InitialContext {
    public IAlertTargetService lookup(String serviceName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (IAlertTargetService) Class.forName("AlertTarget."+serviceName).newInstance();
    }
}

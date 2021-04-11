package ServiceLocator;

import AlertTarget.IAlertTargetService;

public interface IServiceLocator {
	public <T> T getService(String targetName) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}

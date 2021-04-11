package ServiceLocator;

import AlertTarget.FakeService;
import AlertTarget.IAlertTargetService;

public class FakeServiceLocator implements IServiceLocator {
	FakeService fakeService;
	public FakeServiceLocator(FakeService service){
		fakeService = service;
	}
    public IAlertTargetService getService(String targetName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	if(!targetName.equalsIgnoreCase("Fake")) {
    	throw new ClassNotFoundException();
    	}
    	return fakeService;
    }

}

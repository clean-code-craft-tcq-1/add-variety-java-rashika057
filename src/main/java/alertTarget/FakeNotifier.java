package alertTarget;

import typeWiseBreachAlert.BreachType;

public class FakeNotifier implements IAlertTargetObserver {
	String  msg;
	public void send(BreachType breachType) {
        msg = "The temperature is "+breachType.getDisplayName();
  }
	public String getMsg() {
		return msg;
	}

}

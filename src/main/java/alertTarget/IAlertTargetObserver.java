package alertTarget;

import typeWiseBreachAlert.BreachType;

public interface IAlertTargetObserver {
	void send(BreachType breachType);
}

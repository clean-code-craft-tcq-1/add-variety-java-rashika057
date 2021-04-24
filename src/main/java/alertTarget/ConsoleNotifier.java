package alertTarget;

import typeWiseBreachAlert.BreachType;

public class ConsoleNotifier implements IAlertTargetObserver {
	   
    public void send(BreachType breachType) {
            System.out.println("The temperature is "+breachType.getDisplayName()+"\n");
      }
}

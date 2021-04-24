package alertTarget;

import typeWiseBreachAlert.BreachType;

public class ControllerNotifier implements IAlertTargetObserver {
    
    public void send(BreachType breachType) {
      int header = 0xfeed;
      System.out.printf("%d : %s%n", header, breachType.getDisplayName()+"\n");
    }
}

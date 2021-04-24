package alertTarget;

import typeWiseBreachAlert.BreachType;

public class EmailNotifier implements IAlertTargetObserver {
	
	 public void send(BreachType breachType) {
	      String recepient = "a.b@c.com";	    	  
	      System.out.printf("To: %s%n", recepient);
	      System.out.println("Hi, the temperature is "+breachType.getDisplayName()+"\n");
	    }

}

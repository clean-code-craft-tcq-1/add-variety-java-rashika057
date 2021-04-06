package TypewiseAlert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypewiseAlert 
{    
    public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
      if(value < lowerLimit) {
        return BreachType.TOO_LOW;
      }
      if(value > upperLimit) {
        return BreachType.TOO_HIGH;
      }
      return BreachType.NORMAL;
    }
   
    public static BreachType classifyTemperatureBreach(
        CoolingType coolingType, double temperatureInC) {
      return inferBreach(temperatureInC, coolingType.getLowerLimit(), coolingType.getUpperLimit());
    }
    
    public static void checkAndAlert(
        AlertTarget alertTarget, BatteryCharacter batteryChar, double temperatureInC) {

      BreachType breachType = classifyTemperatureBreach(
        batteryChar.getCoolingType(), temperatureInC
      );
      Method method;
	try {
		method = TypewiseAlert.class.getMethod("send" + alertTarget.getDisplayName(),BreachType.class);
		method.invoke(TypewiseAlert.class,breachType);
	} catch (NoSuchMethodException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		e.printStackTrace();
	}
    }
    
    public static void sendToController(BreachType breachType) {
      int header = 0xfeed;
      System.out.printf("%d : %s%n", header, breachType.getDisplayName()+"\n");
    }
    public static void sendToEmail(BreachType breachType) {
      String recepient = "a.b@c.com";
      if(!breachType.getDisplayName().equalsIgnoreCase("normal")) {
    	  System.out.printf("To: %s%n", recepient);
          System.out.println("Hi, the temperature is "+breachType.getDisplayName()+"\n");
      }      
    }
    public static void sendToConsole(BreachType breachType) {
            System.out.println("The temperature is "+breachType.getDisplayName()+"\n");
      }
}

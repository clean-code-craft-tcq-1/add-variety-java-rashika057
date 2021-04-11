package TypewiseAlert;

import AlertTarget.IAlertTargetService;
import ServiceLocator.IServiceLocator;
public class TypewiseAlert 
{  
	 IServiceLocator locator;
	TypewiseAlert(IServiceLocator locator){
		this.locator = locator;
	}
    public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
    	if(Double.isNaN(value)) {
    		throw new IllegalArgumentException();
    	}
      if(value < lowerLimit) {
        return BreachType.TOO_LOW;
      }
      else if(value > upperLimit) {
        return BreachType.TOO_HIGH;
      }
      return BreachType.NORMAL;
    }
   
    public  static BreachType classifyTemperatureBreach(
        CoolingType coolingType, double temperatureInC) {
      return inferBreach(temperatureInC, coolingType.getLowerLimit(), coolingType.getUpperLimit());
    }
    
    public  void checkAndAlert(
     String alertTarget, BatteryCharacter batteryChar, double temperatureInC) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
     BreachType breachType = classifyTemperatureBreach(
      batteryChar.getCoolingType(), temperatureInC
   );
     ((IAlertTargetService) locator.getService(alertTarget)).send(breachType);
    }
   }

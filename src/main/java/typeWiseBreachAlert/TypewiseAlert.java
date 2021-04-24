package typeWiseBreachAlert;

import alertTarget.IAlertTargetObserver;

public class TypewiseAlert 
{
	public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
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
    		IAlertTargetObserver alertTarget, BatteryCharacter batteryChar, double temperatureInC) {
     BreachType breachType = classifyTemperatureBreach(
      batteryChar.getCoolingType(), temperatureInC
   );
    alert(alertTarget,breachType);
    }
    
    public void alert(IAlertTargetObserver alertTarget, BreachType breachType) {
    	alertTarget.send(breachType);
   }
}
package TypewiseAlert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TypewiseAlertTest 
{
    @Test
    public void infersBreachAsPerLimits()
    {
      assertTrue(TypewiseAlert.inferBreach(12, 20, 30) ==
        BreachType.TOO_LOW);
    }
    
    @Test
    public void classifyTemperatureBreachAsPerCoolingType()
    {
      assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.MED_ACTIVE_COOLING,50) ==
        BreachType.TOO_HIGH);
      assertFalse(TypewiseAlert.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING,30) ==
    	        BreachType.TOO_LOW);
      assertTrue(TypewiseAlert.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING,-10) ==
  	        BreachType.TOO_LOW);
    }
    
    @Test
    public void checkAndAlertAsPerAlertTarget() {
    	BatteryCharacter batteryCharacter = new BatteryCharacter();
    	batteryCharacter.setBrand("Aptiv");
    	batteryCharacter.setCoolingType(CoolingType.PASSIVE_COOLING);
		TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL,batteryCharacter , 36);
		TypewiseAlert.checkAndAlert(AlertTarget.TO_CONTROLLER,batteryCharacter , 36);
		TypewiseAlert.checkAndAlert(AlertTarget.TO_CONSOLE,batteryCharacter , 36);
    }
}

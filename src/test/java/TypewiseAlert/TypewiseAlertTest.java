package TypewiseAlert;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import AlertTarget.FakeService;
import ServiceLocator.FakeServiceLocator;

@RunWith(Parameterized.class)
public class TypewiseAlertTest 
{
	CoolingType type;
	double tempInC;
	BreachType expectedResult;
	String alertTarget;
	
	public TypewiseAlertTest(CoolingType type, double tempInC,BreachType expectedResult,String alertTarget) {
	      this.type = type;
	      this.tempInC = tempInC;
	      this.expectedResult = expectedResult;
	      this.alertTarget = alertTarget;
	   }

	   @Parameterized.Parameters
	   public static Collection inputData() {
	      return Arrays.asList(new Object[][]{
	         { CoolingType.MED_ACTIVE_COOLING, 50,BreachType.TOO_HIGH,"Email"  },
	         { CoolingType.HI_ACTIVE_COOLING, 30, BreachType.NORMAL,"Controller" },
	         { CoolingType.PASSIVE_COOLING, -10,BreachType.TOO_LOW,"Console"  },
	         { CoolingType.PASSIVE_COOLING, -10,BreachType.TOO_LOW,"Controller"  }
	      });
	   }
	
    @Test
    public void infersBreachAsPerLimits()
    {
      assertEquals(BreachType.TOO_LOW,TypewiseAlert.inferBreach(12, 20, 30));
    }
    
    @Test
    public void classifyTemperatureBreachAsPerCoolingType()
    {
      assertEquals(expectedResult,TypewiseAlert.classifyTemperatureBreach(type,tempInC));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void classifyTemperatureBreachIfTempisNaN()
    {
      assertEquals(expectedResult,TypewiseAlert.classifyTemperatureBreach(type,Double.NaN));
    }
    
    @Test
    public  void checkAndAlertAsPerAlertTarget() {
    	BatteryCharacter batteryCharacter = new BatteryCharacter();
    	batteryCharacter.setBrand("Aptiv");
    	batteryCharacter.setCoolingType(type);
		try {
			FakeService fakeService = new FakeService();
			TypewiseAlert typewiseAlert = new TypewiseAlert(new FakeServiceLocator(fakeService));
			typewiseAlert.checkAndAlert("Fake",batteryCharacter , tempInC);
			assertEquals("The temperature is "+expectedResult.getDisplayName(),fakeService.getMsg());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void checkAndAlertIfEmpyAlertTarget() {
    	BatteryCharacter batteryCharacter = new BatteryCharacter();
    	batteryCharacter.setBrand("Aptiv");
    	batteryCharacter.setCoolingType(type);
		try {
			FakeService fakeService = new FakeService();
			TypewiseAlert typewiseAlert = new TypewiseAlert(new FakeServiceLocator(fakeService));
			typewiseAlert.checkAndAlert("",batteryCharacter , tempInC);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			assertEquals(ClassNotFoundException.class,e.getClass());
		}
    }
    
}




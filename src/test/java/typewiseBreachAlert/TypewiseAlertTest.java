package typewiseBreachAlert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import alertTarget.CompositeNotifier;
import alertTarget.ConsoleNotifier;
import alertTarget.ControllerNotifier;
import alertTarget.EmailNotifier;
import alertTarget.FakeNotifier;
import alertTarget.IAlertTargetObserver;
import typeWiseBreachAlert.BatteryCharacter;
import typeWiseBreachAlert.BreachType;
import typeWiseBreachAlert.CoolingType;
import typeWiseBreachAlert.TypewiseAlert;

@RunWith(Parameterized.class)
public class TypewiseAlertTest 
{
	CoolingType type;
	double tempInC;
	BreachType expectedResult;
	String alertTarget;
	BatteryCharacter batteryCharacter;
	TypewiseAlert typewiseAlert;
	String expectedMessage;
	FakeNotifier observer;
	
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@Parameterized.Parameters
	public static Collection inputData() {
	      return Arrays.asList(new Object[][]{
	         { CoolingType.MED_ACTIVE_COOLING, 50,BreachType.TOO_HIGH,"Email","The temperature is "+BreachType.TOO_HIGH.getDisplayName()  },
	         { CoolingType.HI_ACTIVE_COOLING, 30, BreachType.NORMAL,"Controller","" },
	         { CoolingType.PASSIVE_COOLING, -10,BreachType.TOO_LOW,"Console","The temperature is "+BreachType.TOO_LOW.getDisplayName()  },
	         { CoolingType.PASSIVE_COOLING, -10,BreachType.TOO_LOW,"Controller","The temperature is "+BreachType.TOO_LOW.getDisplayName()  }
	      });
	}
	
	public TypewiseAlertTest(CoolingType type, double tempInC,BreachType expectedResult,String alertTarget,String expectedMessage) {
	      this.type = type;
	      this.tempInC = tempInC;
	      this.expectedResult = expectedResult;
	      this.alertTarget = alertTarget;
	      this.expectedMessage = expectedMessage;
	   }
	
	@Before
    public void setup() { 		
		batteryCharacter = new BatteryCharacter();
    	batteryCharacter.setBrand("Aptiv");
    	batteryCharacter.setCoolingType(type);
		typewiseAlert = new TypewiseAlert();
		System.setOut(new PrintStream(outputStreamCaptor));		
	}
	
    @Test
    public void infersBreachAsPerLimits()
    {
      //verify
      assertEquals(BreachType.TOO_LOW,TypewiseAlert.inferBreach(12, 20, 30));
    }
    
    @Test
    public void givenCoolingType_WhenClassifyTemperatureBreach_thenReturnedExpectedResult()
    {
    	//verify
      assertEquals(expectedResult,TypewiseAlert.classifyTemperatureBreach(type,tempInC));
    }
    
    @Test
    public  void givenBatteryCharacterTempAndNotifier_whenCheckAndAlert_thenExpectedMessage() {
			//given
			observer = new FakeNotifier();
			//when
			typewiseAlert.checkAndAlert(observer,batteryCharacter , tempInC);
			//verify
			assertEquals(expectedMessage,observer.getMsg());
	    }
    
    @Test
    public  void givenBatteryCharacterTempAndCompositeNotifier_whenCheckAndAlert_thenExpectedMessage() {
			//given
    		CompositeNotifier _compositeNotifierReference=new CompositeNotifier();
    		_compositeNotifierReference.add(new EmailNotifier());
    		_compositeNotifierReference.add(new ConsoleNotifier());
    		_compositeNotifierReference.add(new ControllerNotifier());
    		IAlertTargetObserver _compositeObserver=_compositeNotifierReference;
			//when
			typewiseAlert.checkAndAlert(_compositeObserver,batteryCharacter , tempInC);
			//verify
			assertTrue(outputStreamCaptor.toString()
				      .trim().contains(expectedMessage));
	    }
    
    @Test(expected = NullPointerException.class)
    public void givenAlertTarget_whenAlert_thenThrowsExceptionIfAlertEmpty(){
    	//when	
			typewiseAlert.alert(observer,BreachType.TOO_HIGH);
		}
    
}




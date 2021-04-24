package alertTarget;

import java.util.ArrayList;
import java.util.List;

import typeWiseBreachAlert.BreachType;

public class CompositeNotifier implements IAlertTargetObserver {
	 List<IAlertTargetObserver> _notifiers=new ArrayList<>();
	 
	    public void add(IAlertTargetObserver observer){
	        _notifiers.add(observer);
	    }
	    private void notify(BreachType breachType){
	        this._notifiers.stream().forEach(notifier -> {
	        	notifier.send(breachType);;
	        });
	    }

		@Override
		public void send(BreachType breachType) {
			 this.notify(breachType);
			
		}
}


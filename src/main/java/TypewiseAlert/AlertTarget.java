package TypewiseAlert;

public enum AlertTarget{
    TO_CONTROLLER("ToController"),
    TO_EMAIL("ToEmail"),
	TO_CONSOLE("ToConsole");
	
	private String displayName;
	
	private AlertTarget(String displayName) {
        this.displayName = displayName;
    }

	public String getDisplayName() {
		return displayName;
	}

  }

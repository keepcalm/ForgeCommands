package keepcalm.mods.forgecommands.api;

import java.util.regex.Pattern;

public enum EnumOn {
	TRUE,
	FALSE;
	
	private boolean isTrue;
	private static final Pattern true_ = Pattern.compile("(on|true|yes|ok|y", Pattern.CASE_INSENSITIVE);

	
	public static boolean isTrue(EnumOn en) {
		if (en == TRUE) {
			return true;
		}
		return false;
	}
	
	public boolean isTrue() {
		if (this == TRUE) {
			return true;
		}
		return false;
	}
	
	public static EnumOn getEnum(String x) {
		if (true_.matcher(x).matches()) {
			return TRUE;
		}
		
		return FALSE;
	}
}

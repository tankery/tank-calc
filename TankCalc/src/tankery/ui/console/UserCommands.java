package tankery.ui.console;

public final class UserCommands {

	public static boolean userWhatsQuit(String input) {
		final String QuitString[] = {
			"quit",
			"exit"
		};
		
		for (int i = 0; i < QuitString.length; i++) {
			if (input.equals(QuitString[i])) {
				return true;
			}
		}
		
		return false;
	}

}

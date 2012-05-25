package tankery.ui.console;

public final class ExitCode {

	public static final int ExitNormal = 0;
	public static final int DontExit = 1;
	
	public static boolean notExit(int exitCode) {
		return exitCode > 0;
	}

}

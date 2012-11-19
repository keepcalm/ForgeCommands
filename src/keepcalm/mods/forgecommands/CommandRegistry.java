package keepcalm.mods.forgecommands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.src.ICommand;
import net.minecraft.src.ServerCommandManager;

public class CommandRegistry {
	private static List<ICommand> commands = new ArrayList<ICommand>();
	
	/**
	 * register command
	 * 
	 * @param cls - class implementing ICommand to register.
	 * @return false if the registration failed for some reason
	 */
	public static boolean registerCommand(Class<? extends ICommand> cls) {
		try {
			ICommand inst = cls.newInstance();
			commands.add(inst);
		} catch (Exception e) {
			CmdsContainer.myLog.log(Level.SEVERE, "The ICommand " + cls.getName() + " failed to register, due to an exception", e);
			return false;
		}
		return true;
	}
	
	public static void registerCommand(ICommand cls) {
		commands.add(cls);
	}
	
	static void registerICommands(ServerCommandManager scm) {
		for (ICommand j : commands) {
			scm.registerCommand(j);
			CmdsContainer.myLog.info("Successfully registered command: " + j.getCommandName());
		}
	}
}

package keepcalm.mods.forgecommands.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import keepcalm.mods.forgecommands.CmdsContainer;

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
			CmdsContainer.logger.log(Level.SEVERE, "The ICommand " + cls.getName() + " failed to register, due to an exception", e);
			return false;
		}
		return true;
	}
	
	/**
	 * Register an already-instantiated instance of an ICommand
	 * @param cls
	 */
	public static void registerCommand(ICommand cls) {
		commands.add(cls);
	}
	
	public static void registerICommands(ServerCommandManager scm) {
		for (ICommand j : commands) {
			scm.registerCommand(j);
			CmdsContainer.logger.info("Successfully registered command: " + j.getCommandName());
		}
	}
}

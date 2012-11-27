package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecore.ChatColor;
import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;

public class CommandWhoAmI extends CommandBase {

	@Override
	public String getCommandName() {
		return "whoami";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender var1) {
		return true;
	}
	
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		var1.sendChatToPlayer("You are " + ChatColor.GREEN + var1.getCommandSenderName() + ChatColor.RESET + ".");
	}

}

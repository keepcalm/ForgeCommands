package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.api.VanishManager;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WrongUsageException;
import static keepcalm.mods.forgecommands.api.VanishManager.*;

public class CommandVanish extends CommandBase {

	@Override
	public String getCommandName() {
		return "vanish";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (!(var1 instanceof EntityPlayerMP)) {
			throw new WrongUsageException("Need to be in-game!");
		}
		
		
		if (toggleVanish(var1.getCommandSenderName())) {
			var1.sendChatToPlayer("You are now " + (!isVanished(var1.getCommandSenderName()) ? "" : "un") + "vanished");
		}
		else {
			var1.sendChatToPlayer((!isVanished(var1.getCommandSenderName()) ? "Unv" : "V") + "anish FAILED!");
		}
	}

}

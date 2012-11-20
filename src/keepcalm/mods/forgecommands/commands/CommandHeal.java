package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import keepcalm.mods.forgecore.ChatColor;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WrongUsageException;

public class CommandHeal extends CommandBase {

	@Override
	public String getCommandName() {
		return "heal";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length == 2) {
			// heal <player> <amount>
			EntityPlayerMP targ = PlayerUtilities.getPlayerFromUsername(var2[0]);
			if (targ == null) {
				var1.sendChatToPlayer(var2[0] + " is not online right now.");
			}
			int amount = this.parseInt(var1, var2[1]);
			targ.heal(amount);
			targ.sendChatToPlayer(ChatColor.GREEN + "You have been healed by " + ChatColor.WHITE + var1.getCommandSenderName());
			var1.sendChatToPlayer("Done!");
		}
		else if (var2.length == 1) {
			EntityPlayerMP targ = PlayerUtilities.getPlayerFromUsername(var2[0]);
			if (targ == null) {
				if (!(var1 instanceof EntityPlayerMP)) {
					throw new WrongUsageException("/heal <player> [amount]");
				}
				targ = (EntityPlayerMP) var1;
				targ.heal(parseInt(var1, var2[0]));
			}
			else {
				targ.setEntityHealth(20);
			}
		}
		else {
			EntityPlayerMP targ = getCommandSenderAsPlayer(var1);
			targ.setEntityHealth(20);
		}
	}

}

package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecore.ChatColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WrongUsageException;

public class CommandFeed extends CommandBase {

	@Override
	public String getCommandName() {
		return "/feed";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length < 1) {
			EntityPlayerMP targ = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var2[0]);
			if (targ != null) {
				targ.getFoodStats().setFoodLevel(20);
				targ.getFoodStats().setFoodSaturationLevel(5.0F);
				targ.sendChatToPlayer(ChatColor.GREEN + "Your hunger was sated." + ChatColor.RESET);
				var1.sendChatToPlayer(ChatColor.GREEN + var2[0] + "'s hunger was sated." + ChatColor.RESET);
			}
			else {
				var1.sendChatToPlayer(var2[0] + " is not online right now. Sorry.");
				return;
			}
		}
		else {
			if (!(var1 instanceof EntityPlayerMP)) {
				throw new WrongUsageException("/feed <player> when used from the console!");
			}
			EntityPlayerMP targ = super.getCommandSenderAsPlayer(var1);
			targ.getFoodStats().setFoodLevel(20);
			targ.getFoodStats().setFoodSaturationLevel(5.0F);
			targ.sendChatToPlayer(ChatColor.GREEN + "Your hunger was sated." + ChatColor.RESET);
		}
	}

}

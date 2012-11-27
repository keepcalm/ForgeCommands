package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.api.TimeParser;
import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import keepcalm.mods.forgecore.ChatColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.Packet4UpdateTime;
import net.minecraft.src.WrongUsageException;

/**
 * TODO: rewrite class for cleanliness reasons ;) 
 */
public class CommandPTime extends CommandBase {

	@Override
	public String getCommandName() {
		return "ptime";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length >= 2) {
			// ptime <time> player
			updatePlayerTime(var1, var2);
		}
		else if (var2.length == 1) {

			updatePlayerTime(var1,new String[] {var2[0], var1.getCommandSenderName()});
		}
	}

	private void updatePlayerTime(ICommandSender var1, String[] var2 ) {
		long newTime;
		boolean reset = false;
		try {
			newTime = TimeParser.parse(var2[0]);
		}
		catch (NumberFormatException e) {
			if (TimeParser.meansReset(var2[0])) {
				reset = true;
			}
			var1.sendChatToPlayer(ChatColor.RED + var2[0] + ChatColor.RESET + " is not a valid time!");
			return;
		}

		if (var2[1].equals('*')) {
			/* run for each guy */
			for (String user : MinecraftServer.getServer().getAllUsernames()) {
				updateTimeForPlayer(PlayerUtilities.getPlayerFromUsername(user), newTime, reset);
			}
		}
		else {

			EntityPlayerMP targ = PlayerUtilities.getPlayerFromUsername(var2[1]);
			
			if (targ == null) {
				throw new WrongUsageException("That player is not online!");
			}
			
			updateTimeForPlayer(targ, newTime, reset);
		}
		var1.sendChatToPlayer("Done!");
	}

	private void updateTimeForPlayer(EntityPlayerMP targ, long newTime, boolean reset) {
		// Packet4UpdateTime(totalWorldTime, ticks);
		if (reset) {
			newTime = targ.worldObj.getWorldTime();
		}
		else {
			newTime += targ.worldObj.getTotalWorldTime();
		}
		Packet4UpdateTime pack = new Packet4UpdateTime(newTime, (newTime / 20) / 60);
		targ.playerNetServerHandler.handleUpdateTime(pack);
	}
}

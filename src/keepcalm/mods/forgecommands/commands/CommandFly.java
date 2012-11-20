package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.api.EnumOn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.Packet202PlayerAbilities;
import net.minecraft.src.PlayerCapabilities;
import net.minecraft.src.WrongUsageException;

public class CommandFly extends CommandBase {

	@Override
	public String getCommandName() {
		return "/fly";
	}

	@Override
	/**
	 * Varied versions of the same code.
	 */
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length >= 2) {
			String action = var2[1];
			String target = var2[0];
			EntityPlayerMP targ = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(target);
			PlayerCapabilities pc = targ.capabilities;
			pc.allowFlying = EnumOn.getEnum(action).isTrue();
			Packet202PlayerAbilities pack = new Packet202PlayerAbilities(pc);
			targ.capabilities = pc;
			targ.playerNetServerHandler.handlePlayerAbilities(pack);
			targ.sendChatToPlayer("You can" + (pc.allowFlying ? " " : "not ") + "fly.");
			var1.sendChatToPlayer("Done!");
		}
		else if (var2.length == 1) {
			EntityPlayerMP targ = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var2[0]);
			if (targ == null) {
				if (!(var1 instanceof EntityPlayerMP)) {
					throw new WrongUsageException("Either " + var2[0] + " is not online, or you are the console.");
				}
				else {
					targ = (EntityPlayerMP) var1;
					PlayerCapabilities pc = targ.capabilities;
					pc.allowFlying = !pc.allowFlying;
					Packet202PlayerAbilities pack = new Packet202PlayerAbilities(pc);
					targ.capabilities = pc;
					targ.playerNetServerHandler.handlePlayerAbilities(pack);
					targ.sendChatToPlayer("You can" + (pc.allowFlying ? " " : "not ") + "fly.");
				}
			}
			else {
				targ = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var2[0]);
				PlayerCapabilities pc = targ.capabilities;
				pc.allowFlying = !pc.allowFlying;
				Packet202PlayerAbilities pack = new Packet202PlayerAbilities(pc);
				targ.capabilities = pc;
				targ.playerNetServerHandler.handlePlayerAbilities(pack);
				targ.sendChatToPlayer("You can" + (pc.allowFlying ? " " : "not ") + "fly.");
				var1.sendChatToPlayer("Done!");
			}
			
		}
	}

}

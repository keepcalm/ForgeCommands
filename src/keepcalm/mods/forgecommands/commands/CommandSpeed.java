package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.Packet202PlayerAbilities;
import net.minecraft.src.Packet21PickupSpawn;
import net.minecraft.src.WrongUsageException;

public class CommandSpeed extends CommandBase {

	@Override
	public String getCommandName() {
		return "speed";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayerMP targetPlayer;
		if (var2.length > 1) {
			targetPlayer = PlayerUtilities.getPlayerFromUsername(var2[1]);
		}
		else {
			if (!(var1 instanceof EntityPlayerMP)) {
				throw new WrongUsageException("/speed <speed> <player>");
			}
			targetPlayer = (EntityPlayerMP) var1;
		}
		float speed;
		try {
			speed = Float.parseFloat(var2[0]);
		}
		catch (NumberFormatException e) {
			var1.sendChatToPlayer(var2[0] + " is not a valid number!");
			return;
		}
		
		Packet202PlayerAbilities pack = new Packet202PlayerAbilities(targetPlayer.capabilities);
		pack.setFlySpeed(speed);
		pack.setWalkSpeed(speed);
		targetPlayer.playerNetServerHandler.handlePlayerAbilities(pack);
	}

}

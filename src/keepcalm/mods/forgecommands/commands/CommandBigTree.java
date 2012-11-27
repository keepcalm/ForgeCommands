package keepcalm.mods.forgecommands.commands;

import java.util.Random;

import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import keepcalm.mods.forgecore.ChatColor;
import keepcalm.mods.forgecore.api.permissions.PermissibleSetting;
import keepcalm.mods.forgecore.api.permissions.PermissionFactory;
import keepcalm.mods.forgecore.api.permissions.Permissions;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WrongUsageException;

public class CommandBigTree extends CommandBase {
	
	public CommandBigTree() {
		Permissions.registerPermission(PermissionFactory.getPermissionForArguments(PermissibleSetting.operator, "BigTree", "Create a big tree", "forgecommands.bigtree"));
	}
	
	@Override
	public String getCommandName() {
		return "bigtree";
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender var0) {
		if (!(var0 instanceof EntityPlayer)) {
			return true;
		}
		else {
			return Permissions.doesPlayerHavePermission("forgecommands.bigtree", (EntityPlayer) var0);
		}
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (!(var1 instanceof EntityPlayerMP)) {
			throw new WrongUsageException("Need to be in the world!");
		}
		EntityPlayerMP guy = (EntityPlayerMP) var1;
		
		MovingObjectPosition t = PlayerUtilities.getTargetBlock(guy);
		
		int x = t.blockX;
		int y = t.blockY;
		int z = t.blockZ;
		
		WorldGenBigTree wgbt = new WorldGenBigTree(true);
		
		if (wgbt.generate(guy.worldObj, new Random(), x, y, z)) {
			guy.sendChatToPlayer(ChatColor.GREEN + "Complete!" + ChatColor.RESET);
		}
		else {
			guy.sendChatToPlayer(ChatColor.RED + "Generation failed :(");
		}
	}

}

package keepcalm.mods.forgecommands.commands;

import java.util.ArrayList;

import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import net.minecraft.src.Block;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;

public class CommandBreak extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "break";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayerMP guy = (EntityPlayerMP) var1;
		MovingObjectPosition mop = PlayerUtilities.getTargetBlock(guy);
		int x = mop.blockX;
		int y = mop.blockY;
		int z = mop.blockZ;
		// don't care about drops
		//Block blk = Block.blocksList[guy.worldObj.getBlockId(mop.blockX, mop.blockY, mop.blockZ)];
		//ArrayList<ItemStack> drops = blk.getBlockDropped(guy.worldObj, x, y, z, guy.worldObj.getBlockMetadata(x, y, z), 0);
		guy.worldObj.setBlockWithNotify(mop.blockX, mop.blockY, mop.blockZ, 0);
		//for (ItemStack j : drops) {
		//	worldObj.
		//}
	}

}

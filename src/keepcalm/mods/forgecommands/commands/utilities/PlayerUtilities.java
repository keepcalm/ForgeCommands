package keepcalm.mods.forgecommands.commands.utilities;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3;

public class PlayerUtilities {
	private EntityPlayerMP thePlayer;
	
	public PlayerUtilities(EntityPlayerMP who) {
		thePlayer = who;
	}
	
	public MovingObjectPosition getTargetBlock() {
		float par1 = 0.1F;
		Vec3 var4 = thePlayer.getPosition(256);
        Vec3 var5 = thePlayer.getLook(256);
        Vec3 var6 = var4.addVector(var5.xCoord * par1, var5.yCoord * par1, var5.zCoord * par1);
        return thePlayer.worldObj.rayTraceBlocks(var4, var6);
	}
}

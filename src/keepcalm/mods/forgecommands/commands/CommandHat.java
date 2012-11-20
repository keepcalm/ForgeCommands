package keepcalm.mods.forgecommands.commands;

import net.minecraft.src.CommandBase;
import net.minecraft.src.CommandException;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet201PlayerInfo;
import net.minecraft.src.Packet5PlayerInventory;

public class CommandHat extends CommandBase {
	private static boolean disabled = true;
	@Override
	public String getCommandName() {
		return "hat";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (!(var1 instanceof EntityPlayerMP)) {
			throw new CommandException("Need to be in-game!");
		}
		var1.sendChatToPlayer("This command is presently non-functioning!");
		if (disabled == true)
			return;
		EntityPlayerMP targ = (EntityPlayerMP) var1;
		
		ItemStack newHat = targ.inventory.getCurrentItem();
		if (newHat == null) {
			targ.setCurrentItemOrArmor(1, null);
			System.out.println("Hat removed!");
			
			return;
		}
		System.out.println("Trying to set hat: "+ newHat.getDisplayName());
		targ.setCurrentItemOrArmor(1, newHat);
		System.out.println("Hat: " + targ.inventory.armorInventory[0].getDisplayName());
		
	}

}

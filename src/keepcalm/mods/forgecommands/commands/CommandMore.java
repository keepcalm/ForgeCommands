package keepcalm.mods.forgecommands.commands;

import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WrongUsageException;

public class CommandMore extends CommandBase {

	@Override
	public String getCommandName() {
		return "more";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (!(var1 instanceof EntityPlayerMP)) {
			throw new WrongUsageException("Need to be in-game!");
		}
		
		EntityPlayerMP guy = (EntityPlayerMP) var1;
		
		ItemStack currentItem = guy.inventory.getCurrentItem();
		currentItem.stackSize = currentItem.getMaxStackSize();
		guy.inventory.setInventorySlotContents(guy.inventory.currentItem, currentItem);
		var1.sendChatToPlayer("Done!");
	}

}

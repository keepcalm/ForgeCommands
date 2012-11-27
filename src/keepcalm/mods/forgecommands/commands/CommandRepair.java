package keepcalm.mods.forgecommands.commands;

import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WrongUsageException;

public class CommandRepair extends CommandBase {

	@Override
	public String getCommandName() {
		return "repair";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		
			String choice = var2[1].toLowerCase();
			boolean all = false;
			if (choice == "all") {
				all = true;
			}
			EntityPlayerMP who;
			if (var2.length == 2) {
				String player = var2[1];
				who = PlayerUtilities.getPlayerFromUsername(player);
				if (who == null) {
					throw new WrongUsageException(player + " is not online!");
				}
				
			}
			else {
				if (!(var1 instanceof EntityPlayerMP)) {
					throw new WrongUsageException("need to be ingame!");
				}
				who = (EntityPlayerMP) var1;
			}
			
			if (all) {
				for (int i = 0; i < who.inventory.getSizeInventory(); i++) {
					ItemStack j = who.inventory.getStackInSlot(i);
					if (!j.isStackable() && j.isItemDamaged()) {
						j.setItemDamage(-1);
						who.inventory.setInventorySlotContents(i, j);
					}
					
				}
			}
			else {
				ItemStack j = who.inventory.getCurrentItem();
				if (!j.isStackable() && j.isItemDamaged()) {
					j.setItemDamage(-1);
					int i = who.inventory.currentItem;
					who.inventory.setInventorySlotContents(i, j);
				}
			}
			
	}

}

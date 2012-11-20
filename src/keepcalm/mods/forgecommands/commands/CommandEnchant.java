package keepcalm.mods.forgecommands.commands;

import java.util.HashMap;
import java.util.Map;

import keepcalm.mods.forgecore.ChatColor;
import net.minecraft.src.CommandBase;
import net.minecraft.src.CommandException;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WrongUsageException;

public class CommandEnchant extends CommandBase {
	private static int disabled = 1;
	// trans-table key -> enchantment
	private static Map<String, Enchantment> enchants = new HashMap<String, Enchantment>();
	public CommandEnchant() {
		for (Enchantment j : Enchantment.enchantmentsList) {
			enchants.put(j.getName(), j);
		}
	}
	
	@Override
	public String getCommandName() {
		return "enchant";
	}

	@Override
	@SuppressWarnings("all")
	public void processCommand(ICommandSender var1, String[] var2) {
		var1.sendChatToPlayer(ChatColor.YELLOW + "This command is unimplimented - bug the author if you want it to be implemented!");
		if (disabled > 0) {
			return;
		}
		if (!(var1 instanceof EntityPlayerMP)) {
			throw new CommandException("Can only be run in-game!");
		}
		EntityPlayerMP player = (EntityPlayerMP) var1;
		if (var2.length == 0) {
			var1.sendChatToPlayer(getErrorMessageForPlayer(player));
			throw new WrongUsageException("/enchant <name> <level>");
		}
	}
	
	private String getErrorMessageForPlayer(EntityPlayerMP who) {
		String msg = "Possible enchantments: ";
		
		int x = 0;
		for (String i : enchants.keySet()) {
			String trans = who.getTranslator().translateKey(i);
			if (x == enchants.keySet().size() - 1) {
				msg += " and " + trans;
			}
			else if (x == enchants.keySet().size() -2) {
				msg += trans;
			}
			else {
				msg += trans + ", ";
			}
			x++;
		}
		
		return msg;
	}

}

package keepcalm.mods.forgecommands;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

import keepcalm.mods.forgecommands.api.VanishManager;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class VanishTickHandler implements ITickHandler {
	private static int wait = 0;
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		VanishManager.updatePlayers();
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		VanishManager.updatePlayers();
	}

	@Override
	public EnumSet<TickType> ticks() {
		Collection<TickType> tts = new HashSet<TickType>();
		tts.add(TickType.SERVER);
		tts.add(TickType.PLAYER);
		tts.add(TickType.WORLD);
		
		EnumSet<TickType> j = EnumSet.copyOf(tts);
		return j;
	}

	@Override
	public String getLabel() {
		return "forgecommandsTickHandle";
	}


}

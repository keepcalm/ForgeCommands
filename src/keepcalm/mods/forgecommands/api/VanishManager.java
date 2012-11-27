package keepcalm.mods.forgecommands.api;

import java.util.HashMap;
import java.util.Map;

import static keepcalm.mods.forgecommands.CmdsContainer.logger;
import keepcalm.mods.forgecommands.commands.util.PlayerUtilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityTracker;
import net.minecraft.src.EntityTrackerEntry;
import net.minecraft.src.Packet201PlayerInfo;
import net.minecraft.src.WorldServer;

public class VanishManager {
	private static Map<String,Boolean> vanished = new HashMap<String, Boolean>();
	
	public static boolean setPlayerVanished(String name, boolean vanish) {
		EntityPlayerMP player = PlayerUtilities.getPlayerFromUsername(name);
		if (player == null) {
			return false;
		}
		WorldServer world = (WorldServer) player.worldObj;
		EntityTracker et = world.getEntityTracker();
		
		//Packet201PlayerInfo pack = new Packet201PlayerInfo(name, !vanish, 9999);
		
		for (Object j : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
			if (!(j instanceof EntityPlayerMP)) {
				logger.warning(j + " is not an instance of EntityPlayerMP. Strange.");
				continue;
			}
			
			EntityPlayerMP y = (EntityPlayerMP) j;
			if (y.username == player.username) {
				logger.finest("Skipping player that is being vanished...");
				continue;
			}
			EntityTrackerEntry ete = (EntityTrackerEntry) et.trackedEntityIDs.lookup(player.entityId);
			ete.removePlayerFromTracker(y);
			ete.removeFromWatchingList(y);
			
			//y.playerNetServerHandler.handlePlayerInfo(pack);
		}
		
		vanished.put(name.toLowerCase(), true);
		
		return true;
	}
	
	public static boolean toggleVanish(String name) {
		if (!vanished.containsKey(name.toLowerCase())) {
			return setPlayerVanished(name, true);
		}
		else {
			return setPlayerVanished(name, !vanished.get(name.toLowerCase()));
		}
	}
	
	public static boolean isVanished(String name) {
		try {
			return vanished.get(name);
		}
		catch (Exception e) {
			return false;
		}
	}

	public static void updatePlayers() {
		logger.finest("Resending vanished status...");
		for (String i : vanished.keySet()) {
			if (vanished.get(i)) {
				setPlayerVanished(i, true);
			}
		}
	}
	
	
}

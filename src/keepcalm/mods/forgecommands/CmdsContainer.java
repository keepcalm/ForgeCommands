package keepcalm.mods.forgecommands;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.src.ServerCommandManager;

import keepcalm.mods.forgecommands.api.CommandRegistry;
import keepcalm.mods.forgecommands.commands.CommandBigTree;
import keepcalm.mods.forgecommands.commands.CommandBreak;
import keepcalm.mods.forgecommands.commands.CommandHat;
import cpw.mods.fml.common.MissingModsException;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;


@Mod(modid="ForgeCommands",name="ForgeCommands",version="1.4.4-0")
public class CmdsContainer {
	@Instance("ForgeCommands")
	public static CmdsContainer instance;
	
	public static Logger myLog;
	
	/**
	 * Used by __all__ sub-mods of this package.
	 */
	private static File cfg;
	@PreInit
	public void preInit(FMLPreInitializationEvent ev) {
		myLog = ev.getModLog();
		myLog.info("Hi");
		try {
			getClass().getClassLoader().loadClass("keepcalm.mods.forgecore.ForgeCoreModContainer");
		}
		catch (ClassNotFoundException e) {
			myLog.log(Level.SEVERE, "ForgeCommands requires ForgeCore to be installed! Your game will now stop!");
			ArtifactVersion j = new DefaultArtifactVersion("ForgeCore", true);
			Set<ArtifactVersion> x = new HashSet<ArtifactVersion>();
			x.add(j);
			throw new MissingModsException(x);
		}
		
		CommandRegistry.registerCommand(CommandBigTree.class);
		CommandRegistry.registerCommand(CommandBreak.class);
		CommandRegistry.registerCommand(CommandHat.class);
		cfg = ev.getSuggestedConfigurationFile();
		
		
	}
	
	@ServerStarting
	public void serverStart(FMLServerStartingEvent ev) {
		CommandRegistry.registerICommands((ServerCommandManager) ev.getServer().getCommandManager());
	}
	
}

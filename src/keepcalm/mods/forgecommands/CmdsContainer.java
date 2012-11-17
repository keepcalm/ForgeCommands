package keepcalm.mods.forgecommands;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid="ForgeCommands",name="ForgeCommands",version="1.4.4-0")
public class CmdsContainer {
	@Instance("ForgeCommands")
	public static CmdsContainer instance;
	
	public static Logger myLog;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent ev) {
		myLog = ev.getModLog();
		myLog.info("Hi");
	}
	
}

package com.zaafiir.tutorialmod.init;

import net.minecraftforge.fml.common.event.*;

public interface IHandler {

	public void preInit(FMLPreInitializationEvent event);
	
	public void init(FMLInitializationEvent event);
	
	public void postInit(FMLPostInitializationEvent event);
}

package com.zaafiir.tutorialmod.init;

import java.util.List;

import com.google.common.collect.Lists;
import com.zaafiir.tutorialmod.api.item.TMItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InitHandler implements IHandler{
	
	public static final InitHandler INSTANCE = new InitHandler();


	final List<Item> ITEMS = Lists.newArrayList();
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new TMClientEvents());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
	}
		
}

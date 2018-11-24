package com.zaafiir.tutorialmod.init;

import com.zaafiir.tutorialmod.api.item.TMItems;
import com.zaafiir.tutorialmod.item.ItemBase;
import com.zaafiir.tutorialmod.utill.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ItemHandler implements IHandler{

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		//Create
		TMItems.obsidian_ingot = new ItemBase("obsidian_ingot");
		
		//Register
		registerItem(TMItems.obsidian_ingot, "obsidian_ingot");
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private static void registerItem(Item item, String name){
		InitHandler.INSTANCE.ITEMS.add(item.setRegistryName(new ResourceLocation(Reference.MODID, name)));
	}
	
	

}

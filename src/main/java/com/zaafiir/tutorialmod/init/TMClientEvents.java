package com.zaafiir.tutorialmod.init;

import com.zaafiir.tutorialmod.api.item.TMItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TMClientEvents {

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent event) {
		registerItemRender(TMItems.obsidian_ingot, 0);
		registerItemRender(TMItems.pinot_seeds, 0);
		registerItemRender(TMItems.pinot_grape, 0);
		registerItemRender(TMItems.fire_ball_spell, 0);
	}
	
	protected void registerItemRender(Item item, int meta, String res){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("tm:" + res, "inventory"));
	}
	
	protected void registerItemRender(Item item, int meta){
		registerItemRender(item, meta, item.getRegistryName().getResourcePath());
	}
	
}

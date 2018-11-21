package com.zaafiir.tutorialmod.item;

import com.zaafiir.tutorialmod.TutorialMod;
import com.zaafiir.tutorialmod.init.ModItem;
import com.zaafiir.tutorialmod.utill.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItem.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		
		TutorialMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}

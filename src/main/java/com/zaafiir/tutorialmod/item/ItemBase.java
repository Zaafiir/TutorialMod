package com.zaafiir.tutorialmod.item;

import com.zaafiir.tutorialmod.TutorialMod;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(String name) {
		
		super();
		setUnlocalizedName(name);
		setCreativeTab(TutorialMod.tabTutorial);
	}
}

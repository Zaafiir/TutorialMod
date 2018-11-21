package com.zaafiir.tutorialmod;

	
import com.zaafiir.tutorialmod.init.ModItem;
import com.zaafiir.tutorialmod.proxy.CommonProxy;
import com.zaafiir.tutorialmod.utill.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
	
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TutorialMod {
	
	@Instance
	public static TutorialMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		
	}
	
	
	public static CreativeTabs tabTutorial = new CreativeTabs("tab_tutorial") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItem.OBSIDIAN_INGOT);
		}
	};
	

}

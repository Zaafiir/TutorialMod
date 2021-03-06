package com.zaafiir.tutorialmod;

	
import java.util.ArrayList;
import java.util.List;

import com.zaafiir.tutorialmod.api.item.TMItems;
import com.zaafiir.tutorialmod.init.EntityHandler;
import com.zaafiir.tutorialmod.init.IHandler;
import com.zaafiir.tutorialmod.init.InitHandler;
import com.zaafiir.tutorialmod.init.ItemHandler;
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
	
	@Instance(Reference.MODID)
	public static TutorialMod instance = new TutorialMod();
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	private static List<IHandler> handlers = new ArrayList<IHandler>() {{
		add(InitHandler.INSTANCE);
		add(new ItemHandler());
		add(new EntityHandler());
	}};
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		handlers.forEach(handler -> handler.preInit(event));
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		handlers.forEach(handler -> handler.init(event));
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		handlers.forEach(handler -> handler.postInit(event));
	}
	
	
	public static CreativeTabs tabTutorial = new CreativeTabs("tab_tutorial") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(TMItems.obsidian_ingot);
		}
	};
	

}

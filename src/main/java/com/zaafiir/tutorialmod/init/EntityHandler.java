package com.zaafiir.tutorialmod.init;

import java.util.Locale;

import com.zaafiir.tutorialmod.entity.EntityZombieWithIngot;

import static com.zaafiir.tutorialmod.TutorialMod.instance;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler implements IHandler{
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		registerEntityWithEgg(EntityZombieWithIngot.class, "ingotzombie", 0, 80, 3, true, 0x36A880, 0x012626);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private static void registerEntityWithEgg(Class<? extends Entity> entity, String name, int modid, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primaryColor, int secondaryColor) {
		EntityRegistry.registerModEntity(new ResourceLocation("abyssalcraft", name.toLowerCase(Locale.ENGLISH)), entity,"tm."+ name, modid, instance, trackingRange, updateFrequency, sendsVelocityUpdates, primaryColor, secondaryColor);
	}
}

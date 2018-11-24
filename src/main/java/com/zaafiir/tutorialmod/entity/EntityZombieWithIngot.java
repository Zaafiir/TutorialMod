package com.zaafiir.tutorialmod.entity;

import com.zaafiir.tutorialmod.api.item.TMItems;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityZombieWithIngot extends EntityZombie {

	public EntityZombieWithIngot(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
		
		Object data = super.onInitialSpawn(difficulty, par1EntityLivingData);
		
		if (getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty()) {
			setHeldItem(EnumHand.MAIN_HAND, new ItemStack(TMItems.obsidian_ingot));
		}
		
		return (IEntityLivingData)data;
	}
	
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		EntityItem entityitem = this.entityDropItem(new ItemStack(TMItems.obsidian_ingot), 1.0F);
	}

}

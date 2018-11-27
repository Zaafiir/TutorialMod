package com.zaafiir.tutorialmod.item;

import com.zaafiir.tutorialmod.entity.EntityFireBall;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemFireBallSpell extends ItemBase {

	public ItemFireBallSpell(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if (!worldIn.isRemote)
        {
			EntityFireBall fireball = new EntityFireBall(worldIn, playerIn);
			fireball.cast(playerIn.rotationYaw, playerIn.rotationPitch, 3.0F);
			worldIn.spawnEntity(fireball);
        }
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}

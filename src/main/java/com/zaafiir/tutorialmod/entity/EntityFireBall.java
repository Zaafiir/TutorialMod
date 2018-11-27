package com.zaafiir.tutorialmod.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFireBall extends Entity {

	EntityLivingBase caster;
	
	public EntityFireBall(World worldIn) {
		super(worldIn);
		this.setSize(0.25F, 0.25F);
	}
	
	public EntityFireBall(World worldIn, EntityLivingBase caster) {
		this(worldIn);
		this.setPosition(caster.posX, caster.posY + (double)caster.getEyeHeight(), caster.posZ);
		this.caster = caster;
	}
	
	public void cast(float yaw, float pitch, float velocity) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		
		float f3 = MathHelper.sqrt((double)f * (double)f + (double)f1 * (double)f1 + (double)f2 * (double)f2);
        double x = (double)f / (double)f3;
        double y = (double)f1 / (double)f3;
        double z = (double)f2 / (double)f3;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
	}
	
	@Override
	public void onUpdate() {
		
		if (ticksExisted > 100) {
			this.setDead();
		}
		RayTraceResult test = world.rayTraceBlocks(new Vec3d(posX, posY, posZ),new Vec3d(posX + motionX, posY + motionY, posZ + motionZ));
		if (test != null && test.typeOfHit.equals(RayTraceResult.Type.BLOCK)) {
			this.setDead();
		}
			
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this,  getEntityBoundingBox().offset(motionX, motionY, motionZ).expand(0.125D,0.125D,0.125D));
		int entitycount = entities.size();
		
		for(Entity entity : entities) {
			if (entity instanceof EntityLivingBase) {
				if (entity.equals(this.caster)) {
					entitycount--;
					continue;
				} 
				EntityLivingBase target = (EntityLivingBase)entity;
				target.attackEntityFrom(DamageSource.MAGIC, 5.0F);
				this.setDead();
				break;
			}else {
				entitycount--;
			}
				
		}
		this.setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
	

}

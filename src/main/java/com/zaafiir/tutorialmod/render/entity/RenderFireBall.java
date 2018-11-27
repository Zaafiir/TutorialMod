package com.zaafiir.tutorialmod.render.entity;

import com.zaafiir.tutorialmod.entity.EntityFireBall;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireBall extends Render<EntityFireBall> {

	private static final ResourceLocation FIRE_BALL_TEXTURE = new ResourceLocation("tm", "textures/model/fire_ball");
	
	protected RenderFireBall(RenderManager renderManager) {
		super(renderManager);
		
	}

	public void doRender(EntityFireBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
		this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)y, (float)z);
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFireBall entity) {
		return FIRE_BALL_TEXTURE;
	}
}

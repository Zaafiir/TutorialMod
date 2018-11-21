package com.zaafiir.tutorialmod.entity;

import net.minecraft.world.World;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerInteract;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Tuple;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityDrunkenVillager extends EntityCreature {

	private static final Logger LOGGER = LogManager.getLogger();
    private int randomTickDivider;
    Village village;
    private final InventoryBasic villagerInventory;


    private boolean isLookingForHome;
    private boolean areAdditionalTasksSet;
	
		public EntityDrunkenVillager(World worldIn) {
			super(worldIn);
	        this.setSize(0.6F, 1.95F);
	        this.villagerInventory = new InventoryBasic("Items", false, 8);
	        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
	        this.setCanPickUpLoot(true);
		}

	    


	    protected void initEntityAI()
	    {
	        this.tasks.addTask(0, new EntityAISwimming(this));
	        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
	        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEvoker.class, 12.0F, 0.8D, 0.8D));
	        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVindicator.class, 8.0F, 0.8D, 0.8D));
	        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityVex.class, 8.0F, 0.6D, 0.6D));
	        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
	        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
	        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
	        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
	        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
	        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
	        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	    }

	    protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	    }

	    protected void updateAITasks()
	    {
	        if (--this.randomTickDivider <= 0)
	        {
	            BlockPos blockpos = new BlockPos(this);
	            this.world.getVillageCollection().addToVillagerPositionList(blockpos);
	            this.randomTickDivider = 70 + this.rand.nextInt(50);
	            this.village = this.world.getVillageCollection().getNearestVillage(blockpos, 32);

	            if (this.village == null)
	            {
	                this.detachHome();
	            }
	            else
	            {
	                BlockPos blockpos1 = this.village.getCenter();
	                this.setHomePosAndDistance(blockpos1, this.village.getVillageRadius());

	                if (this.isLookingForHome)
	                {
	                    this.isLookingForHome = false;
	                    this.village.setDefaultPlayerReputation(5);
	                }
	            }
	        }
	        super.updateAITasks();
	    }


	    protected void entityInit()
	    {
	        super.entityInit();
	    }


	    /**
	     * (abstract) Protected helper method to write subclass entity data to NBT.
	     */
	    public void writeEntityToNBT(NBTTagCompound compound)
	    {
	        super.writeEntityToNBT(compound);
	    }

	    /**
	     * (abstract) Protected helper method to read subclass entity data from NBT.
	     */
	    public void readEntityFromNBT(NBTTagCompound compound)
	    {
	        super.readEntityFromNBT(compound);
	        this.setCanPickUpLoot(true);
	    }

	    /**
	     * Determines if an entity can be despawned, used on idle far away entities
	     */
	    protected boolean canDespawn()
	    {
	        return false;
	    }

	    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	    {
	        return SoundEvents.ENTITY_VILLAGER_HURT;
	    }

	    protected SoundEvent getDeathSound()
	    {
	        return SoundEvents.ENTITY_VILLAGER_DEATH;
	    }

	    @Nullable
	    protected ResourceLocation getLootTable()
	    {
	        return LootTableList.ENTITIES_VILLAGER;
	    }


	    /**
	     * Hint to AI tasks that we were attacked by the passed EntityLivingBase and should retaliate. Is not guaranteed to
	     * change our actual active target (for example if we are currently busy attacking someone else)
	     */
	    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
	    {
	        super.setRevengeTarget(livingBase);

	        if (this.village != null && livingBase != null)
	        {
	            this.village.addOrRenewAgressor(livingBase);

	            if (livingBase instanceof EntityPlayer)
	            {
	                int i = -1;

	                this.village.modifyPlayerReputation(livingBase.getUniqueID(), i);

	                if (this.isEntityAlive())
	                {
	                    this.world.setEntityState(this, (byte)13);
	                }
	            }
	        }
	    }

	    /**
	     * Called when the mob's health reaches 0.
	     */
	    public void onDeath(DamageSource cause)
	    {
	        if (this.village != null)
	        {
	            Entity entity = cause.getTrueSource();

	            if (entity != null)
	            {
	                if (entity instanceof EntityPlayer)
	                {
	                    this.village.modifyPlayerReputation(entity.getUniqueID(), -2);
	                }
	                else if (entity instanceof IMob)
	                {
	                    this.village.endMatingSeason();
	                }
	            }
	            else
	            {
	                EntityPlayer entityplayer = this.world.getClosestPlayerToEntity(this, 16.0D);

	                if (entityplayer != null)
	                {
	                    this.village.endMatingSeason();
	                }
	            }
	        }

	        super.onDeath(cause);
	    }

	    public World getWorld()
	    {
	        return this.world;
	    }

	    public BlockPos getPos()
	    {
	        return new BlockPos(this);
	    }

	    public float getEyeHeight()
	    {
	        return 1.62F;
	    }

	    /**
	     * Handler for {@link World#setEntityState}
	     */
	    @SideOnly(Side.CLIENT)
	    public void handleStatusUpdate(byte id)
	    {
	        if (id == 12)
	        {
	            this.spawnParticles(EnumParticleTypes.HEART);
	        }
	        else if (id == 13)
	        {
	            this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
	        }
	        else if (id == 14)
	        {
	            this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
	        }
	        else
	        {
	            super.handleStatusUpdate(id);
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    private void spawnParticles(EnumParticleTypes particleType)
	    {
	        for (int i = 0; i < 5; ++i)
	        {
	            double d0 = this.rand.nextGaussian() * 0.02D;
	            double d1 = this.rand.nextGaussian() * 0.02D;
	            double d2 = this.rand.nextGaussian() * 0.02D;
	            this.world.spawnParticle(particleType, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
	        }
	    }

	    /**
	     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
	     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
	     */
	    @Nullable
	    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	    {
	        return this.finalizeMobSpawn(difficulty, livingdata, true);
	    }

	    public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, @Nullable IEntityLivingData p_190672_2_, boolean p_190672_3_)
	    {
	        p_190672_2_ = super.onInitialSpawn(p_190672_1_, p_190672_2_);
	        return p_190672_2_;
	    }

	    public void setLookingForHome()
	    {
	        this.isLookingForHome = true;
	    }

	    public boolean canBeLeashedTo(EntityPlayer player)
	    {
	        return false;
	    }

	    /**
	     * Called when a lightning bolt hits the entity.
	     */
	    public void onStruckByLightning(EntityLightningBolt lightningBolt)
	    {
	        if (!this.world.isRemote && !this.isDead)
	        {
	            EntityWitch entitywitch = new EntityWitch(this.world);
	            entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	            entitywitch.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitywitch)), (IEntityLivingData)null);
	            entitywitch.setNoAI(this.isAIDisabled());

	            if (this.hasCustomName())
	            {
	                entitywitch.setCustomNameTag(this.getCustomNameTag());
	                entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
	            }

	            this.world.spawnEntity(entitywitch);
	            this.setDead();
	        }
	    }

	    public InventoryBasic getVillagerInventory()
	    {
	        return this.villagerInventory;
	    }

	    /**
	     * Tests if this entity should pickup a weapon or an armor. Entity drops current weapon or armor if the new one is
	     * better.
	     */
	    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
	    {
	        ItemStack itemstack = itemEntity.getItem();
	        Item item = itemstack.getItem();

	        if (this.canVillagerPickupItem(item))
	        {
	            ItemStack itemstack1 = this.villagerInventory.addItem(itemstack);

	            if (itemstack1.isEmpty())
	            {
	                itemEntity.setDead();
	            }
	            else
	            {
	                itemstack.setCount(itemstack1.getCount());
	            }
	        }
	    }

	    private boolean canVillagerPickupItem(Item itemIn)
	    {
	        return itemIn == Items.BREAD || itemIn == Items.POTATO || itemIn == Items.CARROT || itemIn == Items.WHEAT || itemIn == Items.WHEAT_SEEDS || itemIn == Items.BEETROOT || itemIn == Items.BEETROOT_SEEDS;
	    }
    

	    /**
	     * Returns true if villager has enough items in inventory
	     */
	    private boolean hasEnoughItems(int multiplier)
	    {
	        boolean flag = false;

	        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
	        {
	            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

	            if (!itemstack.isEmpty())
	            {
	                if (itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3 * multiplier || itemstack.getItem() == Items.POTATO && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == Items.CARROT && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == Items.BEETROOT && itemstack.getCount() >= 12 * multiplier)
	                {
	                    return true;
	                }

	                if (flag && itemstack.getItem() == Items.WHEAT && itemstack.getCount() >= 9 * multiplier)
	                {
	                    return true;
	                }
	            }
	        }

	        return false;
	    }

	    /**
	     * Returns true if villager has seeds, potatoes or carrots in inventory
	     */
	    public boolean isFarmItemInInventory()
	    {
	        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
	        {
	            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

	            if (!itemstack.isEmpty() && (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.BEETROOT_SEEDS))
	            {
	                return true;
	            }
	        }

	        return false;
	    }

	    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn)
	    {
	        if (super.replaceItemInInventory(inventorySlot, itemStackIn))
	        {
	            return true;
	        }
	        else
	        {
	            int i = inventorySlot - 300;

	            if (i >= 0 && i < this.villagerInventory.getSizeInventory())
	            {
	                this.villagerInventory.setInventorySlotContents(i, itemStackIn);
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        }
	    }
	    
}

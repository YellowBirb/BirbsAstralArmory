package io.github.yellowbirb.birbsastralarmory.init.customentities;

import io.github.yellowbirb.birbsastralarmory.init.BlockInit;
import io.github.yellowbirb.birbsastralarmory.init.EntityInit;
import io.github.yellowbirb.birbsastralarmory.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class BYGBoat extends Boat {
    private static final EntityDataAccessor<Integer> BYG_BOAT_TYPE = SynchedEntityData.defineId(BYGBoat.class, EntityDataSerializers.INT);
    /*
    private static final LootContextParamSet LOOT_CONTEXT_PARAM_SETS = LootContextParamSet.builder()

            .required(BYGLootContextParams.BOAT_TYPE)
            .required(LootContextParams.THIS_ENTITY)
            .required(LootContextParams.ORIGIN)
            .required(LootContextParams.DAMAGE_SOURCE)
            .build();

     */

    public BYGBoat(Level worldIn, double x, double y, double z) {
        this(EntityInit.BYGBOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BYGBoat(EntityType<? extends Boat> boatEntityType, Level worldType) {
        super(boatEntityType, worldType);
    }

    public static ResourceLocation getLootLocation(BYGType type, boolean isChest, boolean isFall) {
        final var regName = EntityInit.BYGBOAT.getId();
        String typeName = type + (isChest ? "_chest" : "");
        return isFall ? new ResourceLocation(regName.getNamespace(), "boats/" + typeName + "_fall") : new ResourceLocation(regName.getNamespace(), "boats/" + typeName);
    }

    /*

    public List<ItemStack> getDrops(DamageSource damageSource, boolean isFall) {
        if (level instanceof ServerLevel serverLevel) {
            LootContext lootContext = new LootContext.Builder(serverLevel)
                    .withParameter(BYGLootContextParams.BOAT_TYPE, getBYGBoatType())
                    .withParameter(LootContextParams.THIS_ENTITY, this)
                    .withParameter(LootContextParams.ORIGIN, position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, damageSource)
                    .create(LOOT_CONTEXT_PARAM_SETS);
            final var regName = Registry.ENTITY_TYPE.getKey(this.getType());
            final var defaultLocation = this.getType().getDefaultLootTable();
            final var any = serverLevel.getServer().getLootTables().get(isFall ? new ResourceLocation(defaultLocation.getNamespace(), defaultLocation.getPath() + "_fall") : defaultLocation);
            if (any != LootTable.EMPTY)
                return any.getRandomItems(lootContext);

            final var lootTable = serverLevel.getServer().getLootTables().get(getLootLocation(getBYGBoatType(), this instanceof BYGChestBoat, isFall));
            return lootTable.getRandomItems(lootContext);
        }
        return List.of();
    }

     */

    @Override
    public @NotNull Item getDropItem() {
        switch (getBYGBoatType()) {
            case OAK:
            default:
                return ItemInit.yggdrasil_chest_boat.get();
        }
    }

    public Block getPlanks() {
        switch (getBYGBoatType()) {
            case OAK:
            default:
                return BlockInit.yggdrasil_planks.get();
        }
    }

    public BYGType getBYGBoatType() {
        return BYGType.byId(this.entityData.get(BYG_BOAT_TYPE));
    }

    public void setBYGBoatType(BYGType boatBYGType) {
        this.entityData.set(BYG_BOAT_TYPE, boatBYGType.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BYG_BOAT_TYPE, BYGType.OAK.ordinal());
    }


    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("BYGType", this.getBYGBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("BYGType", 8)) {
            this.setBYGBoatType(BYGType.getTypeFromString(compound.getString("BYGType")));
        }
    }

    @Override
    public void animateHurt() {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

    /*
    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, @NotNull BlockState state, @NotNull BlockPos pos) {
        ((BoatEntityAccess) this).byg_setLastYd(this.getDeltaMovement().y);
        if (!this.isPassenger()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (((BoatEntityAccess) this).byg_getStatusField() != Boat.Status.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && !this.isRemoved()) {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            getDrops(DamageSource.FALL, true).forEach(this::spawnAtLocation);
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.level.getFluidState((new BlockPos(this.getX(), this.getY(), this.getZ()).below())).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float) (this.fallDistance - y);
            }

        }
    }
     */

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.level.isClientSide && !this.isRemoved()) {
            if (source instanceof IndirectEntityDamageSource && source.getEntity() != null && this.hasPassenger(source.getEntity())) {
                return false;
            } else {
                this.setHurtDir(-this.getHurtDir());
                this.setHurtTime(10);
                this.setDamage(this.getDamage() + amount * 10.0F);
                this.markHurt();
                boolean flag = source.getEntity() instanceof Player player && player.getAbilities().instabuild;
                if (flag || this.getDamage() > 40.0F) {
                    if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                        this.destroy(source);
                    }
                    this.discard();
                }
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        //TODO: Is this right?
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum BYGType {
        OAK("oak", () -> ItemInit.yggdrasil_chest_boat.get());


        private final String name;
        private final Supplier<Item> chestBoatItem;

        BYGType(String string, Supplier<Item> chestBoatItem) {
            this.name = string;
            this.chestBoatItem = chestBoatItem;
        }

        public Supplier<Item> chestBoatItem() {
            return chestBoatItem;
        }

        /**
         * Get a boat type by it's enum ordinal
         */
        public static BYGType byId(int id) {
            BYGType[] aBYGBoatEntity$BYGType = values();
            if (id < 0 || id >= aBYGBoatEntity$BYGType.length) {
                id = 0;
            }

            return aBYGBoatEntity$BYGType[id];
        }

        public static BYGType getTypeFromString(String nameIn) {
            BYGType[] aBYGBoatEntity$BYGType = values();

            for (BYGType BYGType : aBYGBoatEntity$BYGType) {
                if (BYGType.getName().equals(nameIn)) {
                    return BYGType;
                }
            }

            return aBYGBoatEntity$BYGType[0];
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
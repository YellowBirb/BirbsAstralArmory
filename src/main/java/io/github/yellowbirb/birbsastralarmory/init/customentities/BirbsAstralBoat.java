package io.github.yellowbirb.birbsastralarmory.init.customentities;

import io.github.yellowbirb.birbsastralarmory.init.BlockInit;
import io.github.yellowbirb.birbsastralarmory.init.EntityInit;
import io.github.yellowbirb.birbsastralarmory.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BirbsAstralBoat extends Boat {
    private static final EntityDataAccessor<Integer> BYG_BOAT_TYPE = SynchedEntityData.defineId(BirbsAstralBoat.class, EntityDataSerializers.INT);


    public BirbsAstralBoat(Level worldIn, double x, double y, double z) {
        this(EntityInit.birbs_astral_boat.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BirbsAstralBoat(EntityType<? extends BirbsAstralBoat> boatEntityType, Level worldType) {
        super(boatEntityType, worldType);
    }

    public static ResourceLocation getLootLocation(BoatType type, boolean isChest, boolean isFall) {
        final var regName = EntityInit.birbs_astral_boat.getId();
        String typeName = type + (isChest ? "_chest" : "");
        return isFall ? new ResourceLocation(regName.getNamespace(), "boats/" + typeName + "_fall") : new ResourceLocation(regName.getNamespace(), "boats/" + typeName);
    }

    @Override
    public @NotNull Item getDropItem() {
        switch (getBirbsAstralBoatType()) {
            case YGGDRASIL:
            default:
                return ItemInit.yggdrasil_boat.get();
        }
    }

    public Block getPlanks() {
        switch (getBirbsAstralBoatType()) {
            case YGGDRASIL:
            default:
                return BlockInit.yggdrasil_planks.get();
        }
    }

    public BoatType getBirbsAstralBoatType() {
        return BoatType.byId(this.entityData.get(BYG_BOAT_TYPE));
    }

    public void setBirbsAstralBoatType(BoatType boatBoatGType) {
        this.entityData.set(BYG_BOAT_TYPE, boatBoatGType.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BYG_BOAT_TYPE, BoatType.YGGDRASIL.ordinal());
    }


    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("BYGType", this.getBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("BoatType", 8)) {
            this.setBirbsAstralBoatType(BoatType.getTypeFromString(compound.getString("BoatType")));
        }
    }

    @Override
    public void animateHurt() {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

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
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum BoatType {
        YGGDRASIL("yggdrasil");


        private final String name;

        BoatType(String string) {
            this.name = string;
        }

        /**
         * Get a boat type by it's enum ordinal
         */
        public static BoatType byId(int id) {
            BoatType[] aBYGBoatEntity$BYGType = values();
            if (id < 0 || id >= aBYGBoatEntity$BYGType.length) {
                id = 0;
            }

            return aBYGBoatEntity$BYGType[id];
        }

        public static BoatType getTypeFromString(String nameIn) {
            BoatType[] aBYGBoatEntity$BYGType = values();

            for (BoatType BoatType : aBYGBoatEntity$BYGType) {
                if (BoatType.getName().equals(nameIn)) {
                    return BoatType;
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
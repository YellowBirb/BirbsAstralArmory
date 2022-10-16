package io.github.yellowbirb.birbsastralarmory.init.customentities;

import io.github.yellowbirb.birbsastralarmory.init.EntityInit;
import io.github.yellowbirb.birbsastralarmory.init.ItemInit;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class YggdrasilBoat extends Boat {

    public YggdrasilBoat(EntityType<? extends YggdrasilBoat> p_38290_, Level p_38291_) {
        super(p_38290_, p_38291_);
        this.blocksBuilding = true;
    }

    @Override
    public boolean isVehicle() {
        return true;
    }

    public YggdrasilBoat(Level p_38293_, double p_38294_, double p_38295_, double p_38296_) {
        this(EntityInit.yggdrasil_boat.get(), p_38293_);
        this.setPos(p_38294_, p_38295_, p_38296_);
        this.xo = p_38294_;
        this.yo = p_38295_;
        this.zo = p_38296_;
    }

    @Override
    public @NotNull Item getDropItem() {
        return ItemInit.yggdrasil_boat.get();
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
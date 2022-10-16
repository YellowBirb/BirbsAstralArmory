/*
package io.github.yellowbirb.birbsastralarmory.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;

import java.util.List;

public class MountModBoatS2CPacket implements Packet<ClientGamePacketListener> {

    private final int vehicle;
    private final int[] passengers;

    public MountModBoatS2CPacket(Entity p_133276_) {
        this.vehicle = p_133276_.getId();
        List<Entity> list = p_133276_.getPassengers();
        this.passengers = new int[list.size()];

        for(int i = 0; i < list.size(); ++i) {
            this.passengers[i] = list.get(i).getId();
        }

    }

    public MountModBoatS2CPacket(FriendlyByteBuf p_179308_) {
        this.vehicle = p_179308_.readVarInt();
        this.passengers = p_179308_.readVarIntArray();
    }

    public void write(FriendlyByteBuf p_133285_) {
        p_133285_.writeVarInt(this.vehicle);
        p_133285_.writeVarIntArray(this.passengers);
    }

    public void handle(ClientGamePacketListener p_133282_) {
        p_133282_.handleSetEntityPassengersPacket(this);
    }

    public int[] getPassengers() {
        return this.passengers;
    }

    public int getVehicle() {
        return this.vehicle;
    }
}
*/
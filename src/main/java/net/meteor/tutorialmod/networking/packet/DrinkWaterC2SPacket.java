package net.meteor.tutorialmod.networking.packet;

import net.meteor.tutorialmod.networking.ModMessages;
import net.meteor.tutorialmod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrinkWaterC2SPacket {
    private static final String MESSAGE_DRINK_WATER = "message.tutorialmod.drink_water";
    private static final String MESSAGE_NO_WATER = "message.tutorialmod.no_water";

    public DrinkWaterC2SPacket() {
    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if(hasWaterAroundThem(player, level, 2)) {
                player.sendSystemMessage(Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.LIGHT_PURPLE));

                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.5f, level.random.nextFloat() * 0.1F + 0.9F);

                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.addThirst(1);
                    player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst()).withStyle(ChatFormatting.GREEN));
                    ModMessages.setToPlayer(new ThirstDataSyncC2SPacket(thirst.getThirst()), player);

                });
            } else {
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.RED));

                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.addThirst(1);
                    player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst()).withStyle(ChatFormatting.GREEN));
                    ModMessages.setToPlayer(new ThirstDataSyncC2SPacket(thirst.getThirst()), player);
                });
            }
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(2)).filter(state -> state.is(Blocks.WATER)).toArray().length > 0;

    }
}
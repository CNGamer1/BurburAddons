package net.cngamer.burburaddons;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class WishHandler {
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.contains("Maxor is enraged!")) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            player.dropOneItem(false);
            System.out.println("Working");
        }
    }
}

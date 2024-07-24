package net.cngamer.burburaddons;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BurburAddons.MODID, version = BurburAddons.VERSION)
public class BurburAddons
{
    public static final String MODID = "burburaddons";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new BlockDetectingHandler());
    }
}
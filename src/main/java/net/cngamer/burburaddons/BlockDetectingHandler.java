package net.cngamer.burburaddons;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BlockDetectingHandler {

    private static final int min_X = 0;
    private static final int max_X = 2;
    private static final int y_coord = 70;
    private static final int min_Z = 0;
    private static final int max_Z = 2;
    private int deadbushCount = 0;
    private boolean deletedLines = true;

    private final Set hasDeadbush = new HashSet();
    private final Stack storedLocations = new Stack();
    private final Stack deadbushPositions = new Stack();

    private boolean changeGreenLine = false;
    private Vec3 blockOne;
    private Vec3 blockTwo;
    private Vec3 blockThree;
    private Vec3 blockFour;
    private Vec3 blockFive;
    private Vec3 blockSix;
    private Vec3 blockSeven;
    private Vec3 blockEight;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world != null) {
            if (deadbushPositions.size() < 8 && deletedLines) {
                for (int x = min_X; x <= max_X; x++) {
                    for (int z = min_Z; z <= max_Z; z++) {
                        BlockPos BlockPosition = new BlockPos(x, y_coord, z);
                        if (hasDeadbush.contains(BlockPosition)) {
                            continue;
                        }
                        if (world.getBlockState(BlockPosition).getBlock() == Blocks.deadbush) {
                            hasDeadbush.add(BlockPosition);
                            storedLocations.push(BlockPosition);
                            Vec3 BlockPos = new Vec3(x + 0.5, y_coord + 0.5, z + 0.5);
                            deadbushPositions.push(BlockPos);
                            deadbushCount++;
                        }
                    }
                }
            } else {
                deletedLines = false;
                if (deadbushCount == 8) {
                    blockOne = (Vec3) deadbushPositions.get(7);
                    blockTwo = (Vec3) deadbushPositions.get(6);
                    blockThree = (Vec3) deadbushPositions.get(5);
                    blockFour = (Vec3) deadbushPositions.get(4);
                    blockFive = (Vec3) deadbushPositions.get(3);
                    blockSix = (Vec3) deadbushPositions.get(2);
                    blockSeven = (Vec3) deadbushPositions.get(1);
                    blockEight = (Vec3) deadbushPositions.get(0);
                }
                if (deadbushCount > 0) {
                    BlockPos BlockPositions = (BlockPos) storedLocations.get(deadbushCount - 1);
                    if (world.getBlockState(BlockPositions).getBlock() == Blocks.air) {
                        deadbushPositions.pop();
                        storedLocations.pop();
                        hasDeadbush.remove(BlockPositions);
                        switch (deadbushCount) {
                            case 1:
                                blockEight = null;
                                break;
                            case 2:
                                blockSeven = null;
                                break;
                            case 3:
                                blockSix = null;
                                break;
                            case 4:
                                blockFive = null;
                                break;
                            case 5:
                                blockFour = null;
                                break;
                            case 6:
                                blockThree = null;
                                break;
                            case 7:
                                blockTwo = null;
                                break;
                            case 8:
                                blockOne = null;
                                changeGreenLine = true;
                                break;
                        }
                        deadbushCount--;
                    }
                } else {
                    deletedLines = true;
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {

        if (blockOne != null && blockTwo != null) {
            Utils.drawLine(blockOne, blockTwo, Color.GREEN, event.partialTicks);
        }
        if (blockTwo != null && blockThree != null) {
            Utils.drawLine(blockTwo, blockThree, changeGreenLine && blockOne == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
        if (blockThree != null && blockFour != null) {
            Utils.drawLine(blockThree, blockFour, changeGreenLine && blockTwo == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
        if (blockFour != null && blockFive != null) {
            Utils.drawLine(blockFour, blockFive, changeGreenLine && blockThree == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
        if (blockFive != null && blockSix != null) {
            Utils.drawLine(blockFive, blockSix, changeGreenLine && blockFour == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
        if (blockSix != null && blockSeven != null) {
            Utils.drawLine(blockSix, blockSeven, changeGreenLine && blockFive == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
        if (blockSeven != null && blockEight != null) {
            Utils.drawLine(blockSeven, blockEight, changeGreenLine && blockSix == null ? Color.GREEN : Color.RED, event.partialTicks);
        }
    }
}


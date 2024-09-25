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

    private static final int min_X = -54;
    private static final int max_X = -41;
    private static final int y_coord = 70;
    private static final int min_Z = -127;
    private static final int max_Z = -117;
    private final Set hasDeadbush = new HashSet();
    private final Stack storedLocations = new Stack();
    private final Stack deadbushPositions = new Stack();
    private boolean firstTime = true;
    private int deadbushCount = 0;
    private boolean deletedLines = true;
    private boolean changeGreenLine = false;
    private boolean doThatThing = false;
    private Vec3 blockOne;
    private Vec3 blockTwo;
    private Vec3 blockThree;
    private Vec3 blockFour;
    private Vec3 blockFive;
    private Vec3 blockSix;
    private Vec3 blockSeven;
    private Vec3 blockEight;
    private BlockPos block1;
    private BlockPos block2;
    private BlockPos block3;
    private BlockPos block4;
    private BlockPos block5;
    private BlockPos block6;
    private BlockPos block7;
    private BlockPos block8;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world != null) {
            if (firstTime) {
                for (int x = min_X; x <= max_X; x++) {
                    for (int z = min_Z; z <= max_Z; z++) {
                        BlockPos BlockPosition = new BlockPos(x, y_coord, z);
                        if (hasDeadbush.contains(BlockPosition)) {
                            continue;
                        }
                        if (world.getBlockState(BlockPosition).getBlock() == Blocks.deadbush) {
                            hasDeadbush.add(BlockPosition);
                            storedLocations.push(BlockPosition);
                            deadbushCount++;
                        }
                    }
                }
                if (deadbushCount == 8) {
                    doThatThing = true;
                }
                if (doThatThing) {
                    if (deadbushCount == 8) {
                        block1 = (BlockPos) storedLocations.get(0);
                        block2 = (BlockPos) storedLocations.get(1);
                        block3 = (BlockPos) storedLocations.get(2);
                        block4 = (BlockPos) storedLocations.get(3);
                        block5 = (BlockPos) storedLocations.get(4);
                        block6 = (BlockPos) storedLocations.get(5);
                        block7 = (BlockPos) storedLocations.get(6);
                        block8 = (BlockPos) storedLocations.get(7);
                    }
                    if (block1 != null) {
                        if (world.getBlockState(block1).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block1);
                            block1 = null;
                            deadbushCount--;
                        }
                    }
                    if (block2 != null) {
                        if (world.getBlockState(block2).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block2);
                            block2 = null;
                            deadbushCount--;
                        }
                    }
                    if (block3 != null) {
                        if (world.getBlockState(block3).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block3);
                            block3 = null;
                            deadbushCount--;
                        }
                    }
                    if (block4 != null) {
                        if (world.getBlockState(block4).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block4);
                            block4 = null;
                            deadbushCount--;
                        }
                    }
                    if (block5 != null) {
                        if (world.getBlockState(block5).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block5);
                            block5 = null;
                            deadbushCount--;
                        }
                    }
                    if (block6 != null) {
                        if (world.getBlockState(block6).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block6);
                            block6 = null;
                            deadbushCount--;
                        }
                    }
                    if (block7 != null) {
                        if (world.getBlockState(block7).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block7);
                            block7 = null;
                            deadbushCount--;
                        }
                    }
                    if (block8 != null) {
                        if (world.getBlockState(block8).getBlock() == Blocks.air) {
                            storedLocations.pop();
                            hasDeadbush.remove(block8);
                            block8 = null;
                            deadbushCount--;
                        }
                    }
                    if (block1 == null && block2 == null && block3 == null && block4 == null && block5 == null && block6 == null && block7 == null && block8 == null) {
                        doThatThing = false;
                        firstTime = false;
                        deletedLines = true;
                    }
                }
            }
            if (deadbushPositions.size() < 8 && deletedLines && !firstTime) {
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
            } else if (!firstTime) {
                deletedLines = false;
                if (deadbushCount == 8) {
                    blockOne = (Vec3) deadbushPositions.get(0);
                    blockTwo = (Vec3) deadbushPositions.get(1);
                    blockThree = (Vec3) deadbushPositions.get(2);
                    blockFour = (Vec3) deadbushPositions.get(3);
                    blockFive = (Vec3) deadbushPositions.get(4);
                    blockSix = (Vec3) deadbushPositions.get(5);
                    blockSeven = (Vec3) deadbushPositions.get(6);
                    blockEight = (Vec3) deadbushPositions.get(7);
                }
                if (!storedLocations.isEmpty()) {
                    BlockPos BlockPositions = (BlockPos) storedLocations.get(0);
                    if (world.getBlockState(BlockPositions).getBlock() == Blocks.air) {
                        deadbushPositions.remove(0);
                        storedLocations.remove(0);
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


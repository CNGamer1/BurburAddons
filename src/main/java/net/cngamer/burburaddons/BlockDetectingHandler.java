package net.cngamer.burburaddons;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.util.Vec3;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.IBlockState;
import net.cngamer.burburaddons.Utils;
import java.awt.Color;

public class BlockDetectingHandler {
    private static final int min_X = 0;
    private static final int max_X = 100;
    private static final int min_Y = 0;
    private static final int max_Y = 10;
    private static final int min_Z = 0;
    private static final int max_Z = 100;
    private Vec3 blockOne;
    private Vec3 blockTwo;
    private Vec3 blockThree;
    private Vec3 blockFour;
    private Vec3 blockFive;
    private Vec3 blockSix;
    private Vec3 blockSeven;
    private Vec3 blockEight;
    private boolean changeGreenLine = false;

    private boolean inField(int x, int y, int z) {
        return x >= min_X && x <= max_X && y >= min_Y && y <= max_Y && z >= min_Z && z <= max_Z;
    }

    private boolean isDeadBush(IBlockState state) {
        return state.getBlock() == Blocks.deadbush;
    }

    private boolean areVec3Equal(Vec3 vec1, Vec3 vec2) {
        return vec1 != null && vec2 != null && vec1.xCoord == vec2.xCoord && vec1.yCoord == vec2.yCoord && vec1.zCoord == vec2.zCoord;
    }

    @SubscribeEvent
    public void onBlockNeighborNotify(BlockEvent.PlaceEvent event) {
        if (inField(event.pos.getX(), event.pos.getY(), event.pos.getZ()) && isDeadBush(event.placedBlock)) {
            Vec3 blockPos = new Vec3(event.pos.getX() + 0.5, event.pos.getY() + 0.5, event.pos.getZ() + 0.5);
            if (blockEight == null) {
                blockEight = blockPos;
            } else if (blockSeven == null) {
                blockSeven = blockPos;
            } else if (blockSix == null) {
                blockSix = blockPos;
            } else if (blockFive == null) {
                blockFive = blockPos;
            } else if (blockFour == null) {
                blockFour = blockPos;
            } else if (blockThree == null) {
                blockThree = blockPos;
            } else if (blockTwo == null) {
                blockTwo = blockPos;
            } else {
                blockOne = blockPos;
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

    @SubscribeEvent
    public void onBlockBreakEvent(BlockEvent.BreakEvent event) {
        if (inField(event.pos.getX(), event.pos.getY(), event.pos.getZ()) && isDeadBush(event.state)) {
            Vec3 blockBroken = new Vec3(event.pos.getX() + 0.5, event.pos.getY() + 0.5, event.pos.getZ() + 0.5);
            if (areVec3Equal(blockBroken, blockOne)) {
                blockOne = null;
                changeGreenLine = true;
            }
            if (areVec3Equal(blockBroken, blockTwo)) {
                blockTwo = null;
            }
            if (areVec3Equal(blockBroken, blockThree)) {
                blockThree = null;
            }
            if (areVec3Equal(blockBroken, blockFour)) {
                blockFour = null;
            }
            if (areVec3Equal(blockBroken, blockFive)) {
                blockFive = null;
            }
            if (areVec3Equal(blockBroken, blockSix)) {
                blockSix = null;
            }
            if (areVec3Equal(blockBroken, blockSeven)) {
                blockSeven = null;
            }
            if (areVec3Equal(blockBroken, blockEight)) {
                blockEight = null;
            }
        }
    }
}
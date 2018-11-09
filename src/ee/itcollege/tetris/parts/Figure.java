package ee.itcollege.tetris.parts;

import java.util.ArrayList;
import java.util.List;

import ee.itcollege.tetris.lib.CollisionDetector;
import javafx.scene.shape.Shape;

@SuppressWarnings("serial")
public class Figure extends ArrayList<Block> {

    private Block centerBlock;

    public void move(int deltaX, int deltaY) {
        for (Block block : this) {
            block.setX(block.getX() + deltaX * Block.SIZE);
            block.setY(block.getY() + deltaY * Block.SIZE);
        }
    }

    public boolean moveIfPossible(int deltaX, int deltaY, List<? extends Shape> blocks) {
        move(deltaX, deltaY);
        if (CollisionDetector.collide(this, blocks)) {
            move(-deltaX, -deltaY);
            return false;
        }
        return true;
    }

    public void rotate() {
        Block center = getCenterBlock();
        double centerX = center.getX();
        double centerY = center.getY();
        for (Block block : this) {
            if (block != center) {
                double x = block.getX() - centerX;
                double y = block.getY() - centerY;

                block.setX(-y + centerX);
                block.setY(x + centerY);
            }
        }
    }

    public void setCenterBlock(Block centerBlock) {
        this.centerBlock = centerBlock;
    }

    public Block getCenterBlock() {
        if (null == centerBlock) {
            return get(0);
        }
        return centerBlock;
    }


}
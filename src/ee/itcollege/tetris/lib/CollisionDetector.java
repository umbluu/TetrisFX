package ee.itcollege.tetris.lib;


import java.util.List;

import javafx.scene.shape.Shape;

/**
 * JavaFX collision detector for {Shape}s and {List}s of {Shape}s
 * @author Mikk Mangus
 */
public class CollisionDetector {

	public static boolean collide(Shape s1, Shape s2) {
		Shape intersect = Shape.intersect(s1, s2);
		return intersect.getBoundsInLocal().getWidth() != -1;
	}

	public static boolean collide(List<? extends Shape> blocks, Shape s) {
		for (Shape b : blocks) {
			if (collide(s, b)) {
				return true;
			}
		}
		return false;
	}

	public static boolean collide(List<? extends Shape> blocks1, List<? extends Shape> blocks2) {
		for (Shape block : blocks1) {
			if (collide(blocks2, block)) {
				return true;
			}
		}
		return false;
	}

}
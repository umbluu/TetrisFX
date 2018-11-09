package ee.itcollege.tetris.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {

	public static final int SIZE = 20;

	public Block(double x, double y) {
		super(x * SIZE, y * SIZE, SIZE, SIZE);

		setFill(Color.BLACK);
	}

}

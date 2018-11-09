package ee.itcollege.tetris.lib;

import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

public class FigureGenerator {
	public Figure createFigure() {

		Figure figure = new Figure();

		Block block = new Block(0, 0);

		while (figure.size() < 4) {

			if (!CollisionDetector.collide(figure, block)) {
				figure.add(block);
				block = new Block((int)(block.getX() / Block.SIZE),
						(int)(block.getY() / Block.SIZE));
			}

			int random = (int) (Math.random() * 4);
			switch (random) {
				case 1:
					block.setX(block.getX() - Block.SIZE);
					break;
				case 2:
					block.setX(block.getX() + Block.SIZE);
					break;
				case 3:
					block.setY(block.getY() - Block.SIZE);
					break;
				default:
					block.setY(block.getY() + Block.SIZE);
					break;
			}
		}

		return figure;
	}

}

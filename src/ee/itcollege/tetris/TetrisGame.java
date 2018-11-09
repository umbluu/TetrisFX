package ee.itcollege.tetris;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.lib.TetrisCamera;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisGame extends Application {

	public static final int FIELD_HEIGHT = 30;
	public static final int FIELD_WIDHT = 15;

	public static void main(String[] args) {
		TetrisGame.launch(args);
	}

	FigureGenerator figureGenerator = new FigureGenerator();
    ArrayList<Rectangle> gameBorders = new ArrayList<>();
    Pane layout = new Pane();
    Timer timer = new Timer();
    Stage window;

    Figure figure;
    Figure nextFigure = figureGenerator.createFigure();
	private void newFigure() {
		Platform.runLater(() -> {
			if (null != figure) {
				gameBorders.addAll(figure);
			}

			figure = nextFigure;
			nextFigure = figureGenerator.createFigure();
			figure.move(-FIELD_WIDHT / 2 - 3, -1);
			nextFigure.move(FIELD_WIDHT + 3, 3);
			layout.getChildren().addAll(nextFigure);
		});
	}

    private void initGameField() {

        Rectangle border = new Rectangle(0, 0, Block.SIZE, Block.SIZE * FIELD_HEIGHT);
        gameBorders.add(border);

        border = new Rectangle((FIELD_WIDHT - 1) * Block.SIZE, 0, Block.SIZE, Block.SIZE * FIELD_HEIGHT);
        gameBorders.add(border);

        border = new Rectangle(0, (FIELD_HEIGHT - 1) * Block.SIZE, Block.SIZE * FIELD_WIDHT, Block.SIZE);
        gameBorders.add(border);

        layout.getChildren().addAll(gameBorders);
    }

	@Override
    public void start(Stage window) throws Exception {
        this.window = window;
        initGameField();
        newFigure();
        newFigure();

        Scene scene = new Scene(layout,
                Block.SIZE * FIELD_WIDHT + 150,
                Block.SIZE * FIELD_HEIGHT);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!figure.moveIfPossible(0, 1, gameBorders)) {
                    newFigure();
                }
            }
        }, 400, 400);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case LEFT:
                    figure.moveIfPossible(-1, 0, gameBorders);
                    break;
                case RIGHT:
                    figure.moveIfPossible(1, 0, gameBorders);
                    break;
                case DOWN:
                    figure.moveIfPossible(0, 1, gameBorders);
                    break;
                case SPACE:
                    while (figure.moveIfPossible(0, 1, gameBorders))
                        ;
                    break;
                case UP:
                    figure.rotate();
                    if (CollisionDetector.collide(figure, gameBorders)) {
                        for (int i = 0; i < 3; i++) { // rotate back to original position
                            figure.rotate();
                        }
                    }
                    break;
            }
        });

        scene.setCamera(new TetrisCamera());

        window.setOnCloseRequest(e -> System.exit(0));
        window.setScene(scene);
        window.show();
    }

}

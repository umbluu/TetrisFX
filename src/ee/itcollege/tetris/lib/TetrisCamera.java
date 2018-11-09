package ee.itcollege.tetris.lib;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

public class TetrisCamera extends PerspectiveCamera {

	public TetrisCamera() {
		setFieldOfView(100);
		setNearClip(.1);
		setFarClip(1000);
		getTransforms().add(new Rotate(0, Rotate.X_AXIS));
	}
}

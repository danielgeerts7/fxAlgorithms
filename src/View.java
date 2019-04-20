
import javafx.scene.layout.Pane;

public class View extends Pane {

	private int width = 1650;
	private int height = 720;
	
	public View() {
		this.setMinSize(width, height);
	}
}

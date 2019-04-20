
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	
	int currX = 0;
	int currY = 0;
	
	public Player(Image img) {
		super(img);
	}

	public void translate(int x, int y) {
		currX = x;
		currY = y;
		this.setTranslateX(10 + (50 * currX));
		this.setTranslateY(10 + (50 * currY));
	}
	
	public Point2D getPosition() {
		return new Point2D(currX, currY);
	}
	
	public void getCounter() {
		
	}
}

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class SuperAlgoritme extends Pane {
	
	private Label label_name = null;
	protected GridPane pane = null;
	
	protected boolean running = true;

	public SuperAlgoritme(Player p) {
		pane = new GridPane();
		for (int y = 0; y < Controller.array_plot.length; y++) {
			for (int x = 0; x < Controller.array_plot[y].length; x++) {
				if (Controller.array_plot[y][x] == 0) {
					pane.add(new ImageView(new Image("File:img/go_tile.png", 50, 50, false, false)), x, y);
				} else if (Controller.array_plot[y][x]== 1) {
					pane.add(new ImageView(new Image("File:img/block_tile.png", 50, 50, false, false)), x, y);
				}
			}
		}
		this.getChildren().add(0, pane);

		
		this.label_name = new Label(getClass().getName());
		label_name.setTranslateX(250);
		label_name.setTranslateY(450);
		this.getChildren().add(1, label_name);
		
		Thread animator = new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					update(p);
				}
			}
		});
		animator.start();
	}
	
	public abstract void update(Player player);
}

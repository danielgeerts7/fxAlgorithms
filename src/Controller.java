

import java.util.ArrayList;
import java.util.List;

import Dijkstra.Vertex;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Controller {

	private View view = null;
	private GridPane grid = null;
	
	private SuperAlgoritme backtracking = null;
	private SuperAlgoritme dijkstra = null;
	private Player p1 = null;
	private Player p2 = null;
	
	public final static int[][] array_plot = {
			{1,1,1,1,1,1,0,1,1,1,1,1},
			{1,1,0,0,1,1,0,1,0,1,1,1},
			{0,0,0,1,0,0,0,1,0,0,0,0},
			{1,1,0,0,0,1,1,1,0,1,1,0},
			{0,0,0,1,1,1,1,0,0,0,1,0},
			{0,1,1,1,0,0,0,0,1,0,1,0},
			{0,1,1,1,0,1,1,0,1,1,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,0}};
	
	public Controller() {
		view = new View();
		grid = new GridPane();
		grid.setHgap(20);
		grid.setHgap(20);
		
		// Init backtracking
		p1 = new Player(new Image("File:img/player.png", 32, 32, false, false));
		p1.translate(0, 2);
		
		backtracking = new Backtracking(array_plot, p1);
		backtracking.getChildren().add(2, p1);
		grid.add(backtracking, 0, 0);
		
		// Init Dijkstra
		p2 = new Player(new Image("File:img/player.png", 32, 32, false, false));
		p2.translate(0, 2);
		
		List<Vertex> validMoveSpace = new ArrayList<Vertex>();
		
		for (int y = 0; y < array_plot.length; y++) {
			for (int x = 0; x < array_plot[y].length; x++) {
				if (array_plot[y][x] == 0) {
					validMoveSpace.add(new Vertex(x, y));
				}
			}
		}
		
		dijkstra = new Dijkstra(array_plot, validMoveSpace, p2, new Vertex(0, 2), new Vertex(11, 2));
		dijkstra.getChildren().add(2, p2);
		grid.add(dijkstra, 1, 0);
		
		view.getChildren().add(grid);
	}
	
	public View getView() {
		return view;
	}
}

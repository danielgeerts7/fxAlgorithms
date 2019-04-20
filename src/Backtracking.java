import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;

public class Backtracking extends SuperAlgoritme {

	Map<Point2D, Integer> checker = new HashMap<Point2D, Integer>();

	public Backtracking(int[][] array_plot, Player p) {
		super(p);
	}

	@Override
	public void update(Player player) {
		if (running) {
			int x = (int) player.getPosition().getX();
			int y = (int) player.getPosition().getY();
			Point2D temp = new Point2D(x, y);
			// check first above
			if (y - 1 >= 0
					&& Controller.array_plot[y - 1][x] != 1
					&& !checker.containsKey(temp)) {
				player.translate(x, y - 1);
				y = y - 1;
				checker.put(temp, 1);
			}
			// Check then right
			else if (x + 1 < Controller.array_plot[y].length
					&& Controller.array_plot[y][x + 1] != 1
					&& (!checker.containsKey(temp) || checker.containsKey(temp) && checker.get(temp) <= 1)) {
				player.translate(x + 1, y);
				x = x + 1;
				checker.put(temp, 2);
			}
			// Check then down
			else if (y + 1 < Controller.array_plot.length
					&& Controller.array_plot[y + 1][x] != 1
					&& (!checker.containsKey(temp) || checker.containsKey(temp) && checker.get(temp) <= 2)) {
				player.translate(x, y + 1);
				y = y + 1;
				checker.put(temp, 3);
			}
			// Check then left
			else if (x - 1 >= 0
					&& Controller.array_plot[y][x - 1] != 1
					&& (!checker.containsKey(temp) || checker.containsKey(temp) && checker.get(temp) <= 3)) {
				player.translate(x - 1, y);
				x = x - 1;
				checker.put(temp, 4);
			}
			// Else go one step back
			else {
				//Point2D point = player.goToLastPosition();
				//x = (int) point.getX();
				//y = (int) point.getY();
				running = false;
			}

			if (player.getPosition().getX() >= Controller.array_plot[y].length) {
				x = Controller.array_plot[y].length;
				y = Controller.array_plot.length;
				running = false;
			}
			//System.out.println("newX:" + x + ", newY:" + y);
		}
	}
}

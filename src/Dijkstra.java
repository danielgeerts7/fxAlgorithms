import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Dijkstra.DijkstraAlgorithm;
import Dijkstra.Edge;
import Dijkstra.Graph;
import Dijkstra.Vertex;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Dijkstra extends SuperAlgoritme {

	private List<Vertex> nodes = null;
	private List<Vertex> validNodes = null;
	private List<Edge> edges = null;
	private DijkstraAlgorithm dickie = null;
	private LinkedList<Vertex> resultPath = null;

	private static boolean goDijkstra = false;
	private Vertex currentPos = null;
	private static boolean setWaitFlag = false;
	private Pane overlay = null;

	public Dijkstra(int[][] array_plot, List<Vertex> validMoveTiles, Player p, Vertex startlocation,
			Vertex endlocation) {
		super(p);

		Circle start = new Circle(25, Color.GREEN);
		pane.add(start, startlocation.getX(), startlocation.getY());

		Circle end = new Circle(25, Color.RED);
		pane.add(end, endlocation.getX(), endlocation.getY());

		nodes = new ArrayList<Vertex>();
		validNodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		
		for (int y = 0; y < array_plot.length; y++) {
			for (int x = 0; x < array_plot[y].length; x++) {
				nodes.add(new Vertex(x, y));
				if (array_plot[y][x] == 0) {
					validNodes.add(new Vertex(x, y));
				}
			}
		}

		Iterator<Vertex> current = validNodes.iterator();
		Iterator<Vertex> ahead = validNodes.iterator();
		while (current.hasNext()) {
			Vertex curr = current.next();
			while (ahead.hasNext()) {
				Vertex next = ahead.next();
				if (curr.getX() == next.getX() +1 &&
						curr.getY() == next.getY()) {
					addLane(Edge.Direction.RIGHT, curr, next, 1);
				} else if (curr.getX() == next.getX() -1 &&
						curr.getY() == next.getY()) {
					addLane(Edge.Direction.LEFT, curr, next, 1);
				} else if (curr.getX() == next.getX() &&
						curr.getY() == next.getY() - 1) {
					addLane(Edge.Direction.UP, curr, next, 1);
				} else if (curr.getX() == next.getX() &&
						curr.getY() == next.getY() + 1) {
					addLane(Edge.Direction.DOWN, curr, next, 1);
				}
			}
			ahead = validNodes.iterator();
		}

		System.out.println("validNodes: " + validNodes.size());
		System.out.println("Edges: " + edges.size());

		Graph g = new Graph(nodes, edges);
		dickie = new DijkstraAlgorithm(g);
		dickie.execute(startlocation);
		resultPath = dickie.getPath(endlocation);

		assertNotNull(resultPath);
		assertTrue(resultPath.size() > 0);

		goDijkstra = true;

		overlay = new Pane();
		Iterator<Edge> edgeIter = edges.iterator();
		while (edgeIter.hasNext()) {
			Edge temp = edgeIter.next();
			Line node = null;
			switch (temp.getDirection()) {
			case UP:
				node = new Line(0, 0, 0, 50);
				break;
			case DOWN:
				node = new Line(0, 0, 0, -50);
				break;
			case RIGHT:
				node = new Line(0, 0, -50, 0);
				break;
			case LEFT:
				node = new Line(0, 0, 50, 0);
				break;
			}
			node.setStroke(Color.ORANGE);
			node.setTranslateX(25 + temp.getSource().getX() * 50);
			node.setTranslateY(25 + temp.getSource().getY() * 50);
			node.setStrokeWidth(2);
			overlay.getChildren().add(node);
		}
		this.getChildren().add(1, overlay);

		Iterator<Vertex> validIter = validNodes.iterator();
		while (validIter.hasNext()) {
			Vertex temp = validIter.next();
			Circle node = new Circle(3, Color.BLACK);
			node.setTranslateX(25 + temp.getX() * 50);
			node.setTranslateY(25 + temp.getY() * 50);
			overlay.getChildren().add(node);
		}

		Iterator<Vertex> it = resultPath.iterator();
		while (it.hasNext()) {
			Vertex temp = it.next();
			Circle node = new Circle(10, Color.YELLOW);
			node.setTranslateX(25 + temp.getX() * 50);
			node.setTranslateY(25 + temp.getY() * 50);
			overlay.getChildren().add(node);
		}
	}

	@Override
	public void update(Player player) {
		if (goDijkstra) {
			if (currentPos == null) {
				currentPos = resultPath.getFirst();
			}
			Iterator<Vertex> it = resultPath.iterator();
			while (it.hasNext()) {
				Vertex temp = it.next();
				if (currentPos == temp) {
					setWaitFlag = true;
				} else if (setWaitFlag) {
					currentPos = temp;
					setWaitFlag = false;
				}
			}

			player.translate(currentPos.getX(), currentPos.getY());
		}
	}

	private void addLane(Edge.Direction laneId, Vertex sourceLocNo, Vertex destLocNo, int duration) {
		Edge lane = new Edge(laneId, sourceLocNo, destLocNo, duration);
		edges.add(lane);
	}
}

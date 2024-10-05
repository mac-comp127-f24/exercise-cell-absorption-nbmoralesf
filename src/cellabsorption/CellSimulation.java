package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
// import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {

    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Cell> cells;
    
    public static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        cells = new ArrayList<>();
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for (Cell cell : cells) {
                cell.moveAround(canvasCenter);
                cell.grow(0.02);
            }
            // handleInteractions();
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        int nCells = rand.nextInt(20) + 1;

        for (int i = 0; i < nCells; i++) {
            double size = rand.nextInt(5) + 2; 
            Cell cell = new Cell(
                rand.nextDouble() * (canvas.getWidth() - size),
                rand.nextDouble() * (canvas.getHeight() - size),
                size,
                Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
        cells.add(cell);
        canvas.add(cell.getShape());
    }
}
}
// private void handleInteractions() {
//     for (int i = 0; i < cells.size(); i++) {
//         for (int j = i + 1; j < cells.size(); j++) {
//             Cell cell1 = cells.get(i);
//             Cell cell2 = cells.get(j);

//             if (cell1.getShape().getCenter().distance(cell2.getShape().getCenter()) < (cell1.getRadius() + cell2.getRadius())) {
//                 mergeCells(cell1, cell2);
//             }
//         }
//     }
// }

// private void mergeCells(Cell cell1, Cell cell2) {
//     double newRadius = cell1.getRadius() + cell2.getRadius();
//     cell1.setRadius(newRadius);

//     cells.remove(cell2);
//     canvas.remove(cell2.getShape());
// }
// }
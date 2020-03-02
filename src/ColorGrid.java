/* Base UI (i.e. Grid and JPanel) Taken from StackOverflow
 * Link: https://stackoverflow.com/questions/8023468/java-grid-of-clickable-elements
 * User Link: https://stackoverflow.com/users/522444/hovercraft-full-of-eels
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class ColorGrid extends JPanel {
    public static MyColor[][] myColors;
    public static JLabel[][] myLabels;

    public ColorGrid(int rows, int cols, int cellWidth) {
        myColors = new MyColor[rows][cols];
        myLabels = new JLabel[rows][cols];

        MyMouseListener myListener = new MyMouseListener(this);
        Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
        setLayout(new GridLayout(rows, cols));

        int counter = 0;

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int row = 0; row < myLabels.length; row++) {
            ArrayList<Integer> curRow = new ArrayList<>();
            for (int col = 0; col < myLabels[row].length; col++) {
                JLabel myLabel = new JLabel();
                myLabel = new JLabel();
                myLabel.setOpaque(true);
                MyColor myColor = MyColor.GREEN;
                myColors[row][col] = myColor;
                myLabel.setBackground(myColor.getColor());
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                add(myLabel);
                myLabels[row][col] = myLabel;

//                // add the item to the graph
//                MazeMaker.graph.addVertex(counter);
//                counter++;

                // add item to matrix
                curRow.add(counter);
                counter++;
            }
            matrix.add(curRow);
        }

        // create a graph of the grid
        // Integer.MIN_VALUE is a wall
        MazeMaker.graph.matrixToGraph(matrix, Integer.MIN_VALUE, true);

        System.out.println(MazeMaker.graph);
    }

    public MyColor[][] getMyColors() {
        return myColors;
    }

    public void labelPressed(JLabel label, int type) {
        int counter = 0;
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                if (label == myLabels[row][col]) {
                    MyColor myColor = MyColor.GREEN;
                    if (type == 0) {
                        // set wall
                        myColor = MyColor.RED;
                    } else if (type == 1) {
                        // set source
                        myColor = MyColor.BLUE;
                    } else {
                        // set destination
                        myColor = MyColor.YELLOW;
                    }

                    myColors[row][col] = myColor;
                    myLabels[row][col].setBackground(myColor.getColor());

                    // switch type
                    // check that exist to be safe
                    if (MazeMaker.graph.hasVertex(counter)) {
                        if (type == 0) {
                            // change to wall
                            System.out.println("convert to wall");
                            MazeMaker.graph.removeVertexEdges(counter);
                            System.out.println(MazeMaker.graph);
                        } else if (type == 1) {
                            // set source
                            System.out.println("source = " + counter);
                            MazeMaker.source = counter;
                        } else {
                            // set destination
                            System.out.println("destination = " + counter);
                            MazeMaker.destination = counter;
                        }

                    }
                }

                counter++;
            }
        }
    }


}
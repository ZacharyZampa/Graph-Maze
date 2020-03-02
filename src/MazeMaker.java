/* Base UI (i.e. Grid and JPanel) Taken from StackOverflow
 * Link: https://stackoverflow.com/questions/8023468/java-grid-of-clickable-elements
 * User Link: https://stackoverflow.com/users/522444/hovercraft-full-of-eels
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MazeMaker {
    public static Graph<Integer> graph;
    public static int source;
    public static int destination;

    private static void createAndShowGui() {
        int rows = 20;
        int cols = 40;
        int cellWidth = 20;
        ColorGrid mainPanel = new ColorGrid(rows, cols, cellWidth);

        JFrame frame = new JFrame("Color Grid Example");

        JButton runButton = new JButton("Start Maze");
        runButton.setBounds(mainPanel.getHeight() + 1,mainPanel.getWidth() + 1,140, 40);
        frame.add(runButton);


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String onlyVertices = graph.shortestPath(source, destination);
                System.out.println(onlyVertices);
                String[] pathAsArrayString = onlyVertices.split(" ");
                ArrayList<Integer> pathAsArray = new ArrayList<>();
                for (String number : pathAsArrayString) {
                    try {
                        int num = Integer.parseInt(number);
                        pathAsArray.add(num);
                    } catch (Exception ex) {
                        // this was not a number
                    }
                }
                pathDraw(pathAsArray);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void pathDraw(ArrayList<Integer> arr) {
        int counter = 0;
        for (int row = 0; row < ColorGrid.myLabels.length; row++) {
            for (int col = 0; col < ColorGrid.myLabels[row].length; col++) {
                if (arr.contains(counter)) {
                    MyColor myColor = MyColor.WHITE;
                    ColorGrid.myColors[row][col] = myColor;
                    ColorGrid.myLabels[row][col].setBackground(myColor.getColor());
                }
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        graph = new Graph<>();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}


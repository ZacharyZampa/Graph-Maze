/* Base UI (i.e. Grid and JPanel) Taken from StackOverflow
 * Link: https://stackoverflow.com/questions/8023468/java-grid-of-clickable-elements
 * User Link: https://stackoverflow.com/users/522444/hovercraft-full-of-eels
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class MyMouseListener extends MouseAdapter {
    private ColorGrid colorGrid;

    public MyMouseListener(ColorGrid colorGrid) {
        this.colorGrid = colorGrid;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            colorGrid.labelPressed((JLabel)e.getSource(), 0);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // get source
            colorGrid.labelPressed((JLabel)e.getSource(), 1);
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            // get source
            colorGrid.labelPressed((JLabel) e.getSource(), 2);
        }
    }
}
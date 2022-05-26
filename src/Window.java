import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DFSvisual");
        frame.setSize(
                Settings.WINDOW_WIDTH,
                Settings.WINDOW_HEIGHT
        );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));
        Canvas canvas = new Canvas();
        frame.add(canvas);
        frame.setVisible(true);
        frame.setContentPane(canvas);
        frame.setResizable(false);
        frame.addMouseListener(new MyMouseListener(canvas));
        frame.addKeyListener(canvas);


    }

    public static class MyMouseListener implements MouseListener, MouseMotionListener {
        Canvas myCanvas;

        public MyMouseListener(Canvas obj) {
            this.myCanvas = obj;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("Click(" + e.getX() + ", " + e.getY() + ")");
            this.myCanvas.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.myCanvas.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.myCanvas.mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.myCanvas.mouseEntered(e);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.myCanvas.mouseExited(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            this.myCanvas.mouseMoved(e);
            //todo
            System.out.println("move");
        }
    }
}

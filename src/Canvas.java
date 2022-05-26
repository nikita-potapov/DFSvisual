import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Canvas extends JComponent implements ActionListener, KeyListener {

    Timer timer;
    Visualiser visualiser;
    Font pacmanFont;
    ProgramState state;

    public Canvas() {
        super();
        timer = new Timer(Settings.TIMER_DELAY, this);
        initializeCanvas();
        visualiser = new Visualiser();
        timer.start();
        this.state = ProgramState.EDIT;
    }

    public void initializeCanvas() {
        this.setBackground(new Color(225, 225, 225));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(font);

        visualiser.drawFrame(g2d);

        super.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        visualiser.update();
    }

    public void mouseClicked(MouseEvent e) {
        //todo
//        System.out.println("Click(" + e.getX() + ", " + e.getY() + ")");
        visualiser.mouseClicked(e);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.visualiser.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

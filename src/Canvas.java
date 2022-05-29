import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Canvas extends JComponent implements ActionListener, KeyListener {

    Timer timer;
    Visualiser visualiser;
    Font font = new Font("Arial", Font.BOLD, 16);;
    ProgramState state;
    JLabel historyPlotter;
    Stroke initStroke;

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

        g2d.setFont(this.font);

        visualiser.drawFrame(g2d);
        drawInstructions(g2d);

        super.repaint();
    }

    private void drawInstructions(Graphics2D g2d) {
        int text1Height = 20;
        int text2Height = 140;
        int text3Height = 250;
        int text4Height = 370;

        int separatorHeight = 100;

        if (this.initStroke == null)
            this.initStroke = g2d.getStroke();


        Stroke stroke = g2d.getStroke();
        g2d.setStroke(this.initStroke);


        g2d.drawString("Инструкция", 50, text1Height);
        g2d.drawString("1. Кликните по экрану,", 2, text1Height + 20);
        g2d.drawString("левой кнопкой мыши,", 10, text1Height + 40);
        g2d.drawString("чтобы добавить", 10, text1Height + 60);
        g2d.drawString("вершину графа", 10, text1Height + 80);

        g2d.drawLine(0, 25, 200, 25);
        g2d.drawLine(200,  Settings.WINDOW_HEIGHT - separatorHeight, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT - separatorHeight);
        g2d.drawLine(0, text4Height + 45, 200, text4Height + 45);
        g2d.drawLine(200, 0, 200, Settings.WINDOW_HEIGHT);

        g2d.drawString("2. Кликните по двум,", 2, text2Height);
        g2d.drawString("вершинам, чтобы", 15, text2Height + 20);
        g2d.drawString("добавить ребро графа.", 15, text2Height + 40);
        g2d.drawString("Повторите выбор,", 15, text2Height + 60);
        g2d.drawString("чтобы удалить его.", 15, text2Height + 80);

        g2d.drawString("3. Чтобы удалить ", 2, text3Height);
        g2d.drawString("вершину, сначала", 1, text3Height + 20);
        g2d.drawString("удалите все ее ребра,", 1, text3Height + 40);
        g2d.drawString("затем кликните по ней и", 1, text3Height + 60);
        g2d.drawString("нажмите клавишу Delete.", 1, text3Height + 80);

        g2d.drawString("4. Кликните по вершине", 2, text4Height);
        g2d.drawString("правой кнопкой мыши,", 10, text4Height + 20);
        g2d.drawString("чтобы запустить обход.", 10, text4Height + 40);



        g2d.setStroke(stroke);
    }

    public void actionPerformed(ActionEvent e) {
        visualiser.update();
    }

    public void mouseClicked(MouseEvent e) {
        visualiser.mouseClicked(e);
    }

    public void mousePressed(MouseEvent e) {
//        this.visualiser.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        this.visualiser.mouseReleased(e);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        this.visualiser.mouseMoved(e);
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


    public void mouseDragged(MouseEvent e) {
        this.visualiser.mouseDragged(e);
    }
}

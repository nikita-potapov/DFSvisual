package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.company.Settings;

public class MainWindow extends JFrame implements ActionListener {
    JFrame jframe;
    Canvas canvas;
    Timer timer;

    public MainWindow() {
        jframe = new JFrame(Settings.WINDOW_TITLE);
        jframe.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new BorderLayout(1, 1));
        canvas = new Canvas();
        jframe.add(canvas);
        jframe.setVisible(true);
        jframe.getContentPane().setBackground(Color.WHITE);
        jframe.setResizable(false);

        // Таймер для перерисовки изображения
        // 60 кадров в секунду => 1000мс / 60кс = примерно 17 мс\кадр
        this.timer = new Timer(750, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

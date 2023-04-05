package me.window.panel;

import me.window.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GaleryPanel extends JPanel {
    public me.window.Window window;
    public PhotoPanel photoP;
    public GaleryPanel(Window w) {
        this.window = w;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
        this.photoP = new PhotoPanel(this);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color color1 = Color.decode("#434343");
        Color color2 = Color.decode("#000000");
        Graphics2D g2d = (Graphics2D) g.create();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        // Define starting and ending points for the gradient
        Point2D startPoint = new Point2D.Float(0, 0);
        Point2D endPoint = new Point2D.Float(panelWidth, panelHeight);
        GradientPaint gradient = new GradientPaint(startPoint, color1, endPoint, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, panelWidth, panelHeight);
        g2d.dispose();
    }
}

package gui;

import java.awt.*;

public class GraphNodeGui {
    private int x, y;
    private int cx, cy;
    private int radius;
    private Color backgroundColor;
    private String nodeText;

    public void draw(Graphics2D g2d) {
        g2d.setStroke();
        g2d.setPaint(Color.BLACK);
        g2d.drawOval(getX(), getY(), getRadius() * 2, getRadius() * 2);

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public String getNodeText() {
        return nodeText;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
package main.java.edu.guilford;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.File; 

public class RecursiveCirclePanel extends JPanel {

    private int panelHeight;
    private int panelWidth; 
    private double initialX;
    private double initialY;
    private double radius;
    private Random rand = new Random();

    private final double MIN_RADIUS = 4;
    private double reduceFactor = 0.4;

    private ArrayList<ColorEllipse> circles = new ArrayList<ColorEllipse>();

    public RecursiveCirclePanel(int panelHeight, int panelWidth) {
        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        initialX = 500;
        initialY = 400;
        radius = rand.nextDouble() * 20;
        drawAllCircles();
        repaint();
        addMouseListener(new UseMouse());
        addKeyListener(new UseKeys());
    }

    public void drawAllCircles() {
        circles.clear();
        addCircle(initialX, initialY, radius);
        System.out.println("Number of circles: " + circles.size());
        repaint();
        revalidate();
    }

    public void addCircle(double cx, double cy, double radius) {
        ColorEllipse theCircle = new ColorEllipse(cx - radius, cy - radius, 2 * radius, 2 * radius);
        circles.add(theCircle);
        if (radius < MIN_RADIUS) {
            return;
        } else {
            double factor = rand.nextDouble() * reduceFactor + (0.9 - reduceFactor);
            if (factor > 0.9) {
                factor = 0.9;
            } 
            else if (factor <=0) {
                factor = reduceFactor;
            }

            double newRadius = radius * factor;
            double angle = Math.PI * rand.nextDouble();
            double newX = cx + (radius + newRadius) * Math.cos(angle);
            double newY = cy + (radius + newRadius) * Math.sin(angle);
            addCircle(newX, newY, newRadius);
            double newX2 = cx - (radius + newRadius) * Math.cos(angle);
            double newY2 = cy - (radius + newRadius) * Math.sin(angle);
            addCircle(newX2, newY2, newRadius);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
        for (ColorEllipse circle : circles) {
            g2.setColor(circle.getEllipseColor());
            g2.fill(circle);
        }
    }

    private class UseMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            initialX = e.getX();
            initialY = e.getY();
            radius = rand.nextDouble() * 100 + 90;
            drawAllCircles();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            requestFocusInWindow();
        }
        @Override
        public void mouseExited(MouseEvent e) {}

    }

    public class UseKeys implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e);
            if (e.getKeyCode() == KeyEvent.VK_S && e.isShiftDown()) {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();
                printAll(g2d);
                g2d.dispose();
                try {
                    ImageIO.write(image, "png", new File("output.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
        @Override
        public void keyTyped(KeyEvent e) {
        
        }
    }
}

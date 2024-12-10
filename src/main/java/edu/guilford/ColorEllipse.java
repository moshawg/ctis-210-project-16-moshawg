package main.java.edu.guilford;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ColorEllipse extends Ellipse2D.Double {
    private Color ellipseColor;
    private Random rand = new Random();
    
    public ColorEllipse(double x, double y, double w, double h) {
        super(x, y, w, h); 
       
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        int alpha = rand.nextInt(150) + 100;
        
        ellipseColor = new Color(red, green, blue, alpha);
    }
    
    public Color getEllipseColor() {
        return ellipseColor;
    }

    public void setEllipseColor(Color ellipseColor) {
        this.ellipseColor = ellipseColor;
    }

}

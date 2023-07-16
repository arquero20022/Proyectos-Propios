
package views.figuras;

import game.IGameObject;
import game.Position;
import views.AbstractGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;


public class VistaFigHood extends AbstractGameView {
    
   

    public VistaFigHood(IGameObject mObject, int length) throws Exception{
        super(mObject, length);       
    }
    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = gObj.getPosition();
        
        int x0 = coord.getX() * length;
        int y0 = coord.getY() * length;
        int delta = length/12;
        
        Color c = g2.getColor();
        g2.setStroke(new BasicStroke(1));
        
     
        g2.setColor(Color.red);   
        g2.fillRect(x0+4*delta, y0, 3*delta, 4*delta);
        
 
        g2.fillRect(x0+3*delta, y0+5*delta, 5*delta, 5*delta);
        
            
       
        g2.setColor(c);
    }
}



package views.figuras;

import game.IGameObject;
import game.Position;
import views.AbstractGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class VistaFigFly extends AbstractGameView {
    
   
   
    public VistaFigFly(IGameObject mObject, int length) throws Exception{
        super(mObject, length);       
    }
    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = gObj.getPosition();
        
        int x0 = coord.getX() * length;
        int y0 = coord.getY() * length;
        int delta = length/10;
        
        Color c = g2.getColor();
        g2.setColor(Color.BLACK);    
        g2.setStroke(new BasicStroke(1));
        
        
        
  
        g2.setColor(Color.darkGray);    
        g2.fillRect(x0+3*delta, y0, 4*delta, 6*delta);
        
       
        g2.drawRect(x0+4*delta, y0+2*delta, 6*delta, 4*delta);
    
        g2.setColor(c);
    }
}


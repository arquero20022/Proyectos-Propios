
package views.figuras;

import game.IGameObject;
import game.Position;
import views.AbstractGameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class VistaFigDandelion extends AbstractGameView {
    
    Color mC = Color.pink;

    public VistaFigDandelion(IGameObject mObject, int l) throws Exception{
        super(mObject, l);
    }
    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = gObj.getPosition();
        
        Color c = g2.getColor();
        g2.setStroke(new BasicStroke(4));
        
        
        g2.setColor(mC);    
                
        g.drawLine(length * coord.getX(), length * coord.getY(), 
                   length * coord.getX() + length, length * coord.getY() + length );
        g.drawLine(length * coord.getX() + length, length * coord.getY(), 
                   length * coord.getX(), length * coord.getY() + length );
        g.drawLine(length * coord.getX() + length/2, length * coord.getY(), 
                   length * coord.getX()+length/2, length * coord.getY() + length );
        g.drawLine(length * coord.getX(), length * coord.getY()+length/2, 
                   length * coord.getX()+length, length * coord.getY()+length/2 );
        
        g2.setColor(c);
    }   
}
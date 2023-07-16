
package views.figuras;


import game.IGameObject;
import game.Position;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import views.AbstractGameView;


public class VistaFigStone extends AbstractGameView {

    Color myColor = Color.gray;
        
    public VistaFigStone(IGameObject mObject, int l) throws Exception{
        super(mObject, l);
    }
        
    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = gObj.getPosition();
        
        
        Color c = g2.getColor();
        g2.setColor(myColor);    
        g2.setStroke(new BasicStroke(2));
                
        g2.fillOval(
                length * coord.getX() + (int)((1/4.0) * length),
                length * coord.getY() + (int)((1/4.0) * length),      
                (int) ((1/2.0) * length),
                (int) ((1/2.0) * length)
        );
        
                
        g2.setColor(c);
    }
    
}


package views.boxes;

import game.IGameObject;
import game.Position;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import views.AbstractGameView;


public class VistaNumberedCircle extends AbstractGameView {
    
    Color myColor = Color.blue;
    String legend = new String();
    
    public VistaNumberedCircle(IGameObject mObject, int length) throws Exception{
        super(mObject, length); 
    }
    
    public VistaNumberedCircle(IGameObject mObject, int length, Color c) throws Exception{
        super(mObject, length); 
        myColor = c;
    }
    
    public VistaNumberedCircle(IGameObject mObject, int length, Color c, String legend) throws Exception{
        super(mObject, length); 
        myColor = c;
        this.legend = legend;
    }

    
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        Position coord = gObj.getPosition();
        
        Color c = g2.getColor();
        g2.setColor(myColor);    
        g2.setStroke(new BasicStroke(2));
        g2.fillOval(length*coord.getX(), length*coord.getY(), length, length);
        g2.setColor(Color.WHITE);  
        g2.drawString(legend, length*coord.getX()+6, length*coord.getY()+24);
        g2.setColor(c);
    }
}



package views;

import game.Bee;
import game.Blossom;
import game.Fly;
import game.IGameObject;
import game.RidingHood;
import game.Spider;
import views.boxes.VistaNumberedBox;
import views.boxes.VistaNumberedCircle;
import views.icons.VistaIcon;

import java.awt.Color;


public class GameObjectViewFactory {
    

    public static final String BOXES_VIEWS = "BoxesViews"; 
    public static final String ROUNDED_VIEWS = "RoundedViews"; 
    public static final String SQUARE_VIEWS = "SquareViews"; 
    public static final String ICONS_VIEWS = "IconsViews"; 
    
  
    public static IAWTGameView getView(IGameObject gObj, String family, int length) throws Exception{
        
        if (family.compareTo(BOXES_VIEWS) == 0){
            getSquareView(gObj, length);
        }
        else if (family.compareTo(ROUNDED_VIEWS) == 0){
            return getRoundedShape(gObj, length);
        }
        else if (family.compareTo(BOXES_VIEWS) == 0){
            return getSquareView(gObj, length);
        }
        else if (family.compareTo(ICONS_VIEWS) == 0){
            return getIconView(gObj, length);
        }
        
        return getBoxView(gObj, length);
        
    }
    

    public static IAWTGameView getBoxView(IGameObject gObj, int length) throws Exception{    
        
        IAWTGameView view = null;
        
        if (gObj instanceof Fly){
           view = new VistaNumberedCircle(gObj, length, Color.gray, "Fly"); 
        }
        else if (gObj instanceof Bee){
           view = new VistaNumberedCircle(gObj, length, Color.orange, "Bee");
        } 
        else if (gObj instanceof Spider){
           view = new VistaNumberedCircle(gObj, length, Color.black, "Spider"); 
        } 
        else if (gObj instanceof Blossom){
           if (gObj.getValue() < 10){
                view = new VistaNumberedBox(gObj, length, Color.pink, "DLion");  
           }
           else {
                view = new VistaNumberedBox(gObj, length, Color.GREEN, "Clover"); 
           }
        }
        else if (gObj instanceof RidingHood){
           view = new VistaNumberedCircle(gObj, length, Color.red, "Hood"); 
        }  
        return view;        
    }
    
  
    public static IAWTGameView getIconView(IGameObject gObj, int length) throws Exception{
        
        IAWTGameView view = null;
        
        
        if (gObj instanceof Fly){
           view = new VistaIcon(gObj, "src/main/resources/views/fly.jpg", length); 
        }
        else if (gObj instanceof Bee){
           view = new VistaIcon(gObj, "src/main/resources/views/bee.jpg", length); 
        }  
        else if (gObj instanceof RidingHood){
           view = new VistaIcon(gObj, "src/main/resources/views/caperucita.jpg", length); 
        } 
        else if (gObj instanceof Spider){
           view = new VistaIcon(gObj, "src/main/resources/views/spider.jpg", length); 
        } 
        else if (gObj instanceof Blossom){
           if (gObj.getValue() < 10){
                view = new VistaIcon(gObj, "src/main/resources/views/dandelion2.jpg", length); 
           }
           else {
                view = new VistaIcon(gObj, "src/main/resources/views/clover.jpg",  length); 
           }
        }
            
        return view;
    }
    
    public static IAWTGameView getSquareView(IGameObject gObj, int length) throws Exception{        
        return getBoxView(gObj, length); 
    }
    
    public static IAWTGameView getRoundedShape(IGameObject gObj, int length) throws Exception{
        return getIconView(gObj, length);
    }
           
}

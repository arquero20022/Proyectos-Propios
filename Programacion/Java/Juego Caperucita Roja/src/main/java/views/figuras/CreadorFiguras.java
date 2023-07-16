
package views.figuras;

import game.Bee;
import game.Bee;
import game.Blossom;
import game.Fly;
import game.IGameObject;
import game.RidingHood;
import game.Spider;
import game.Stone;
import views.IAWTGameView;
import views.IViewFactory;


public class CreadorFiguras implements IViewFactory {
    
    public IAWTGameView getView(IGameObject gObj, int length) throws Exception {
        
                IAWTGameView view = null;
        
        
        if (gObj instanceof Fly){
           view = new VistaFigFly(gObj, length); 
        }
        else if (gObj instanceof Bee){
           view = new VistaFigBee(gObj, length); 
        }  
        else if (gObj instanceof RidingHood){
           view = new VistaFigHood(gObj, length); 
        } 
        else if (gObj instanceof Spider){
           view = new VistaFigSpider(gObj, length); 
        } 
        else if (gObj instanceof Stone){
            view = new VistaFigStone(gObj, length); 
         } 
        else if (gObj instanceof Blossom){
           if (gObj.getValue() < 10){
                view = new VistaFigDandelion(gObj, length); 
           }
           else {
                view = new VistaFigClover(gObj,  length); 
           }
        }
            
        return view;
    }
    
}

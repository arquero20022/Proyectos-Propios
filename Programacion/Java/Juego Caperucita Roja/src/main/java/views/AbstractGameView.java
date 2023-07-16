
package views;

import game.Blossom;
import game.IGameObject;


public abstract class AbstractGameView implements IAWTGameView{
    
    protected IGameObject gObj;
    protected int length = 20;
    
    public AbstractGameView(IGameObject obj, int length) throws Exception {
        
        if (obj != null){
            gObj = obj;
        }
        else {
            throw new Exception();
        }
        this.length = length;
    }
    
    public static IAWTGameView getView(IGameObject gObj, int length, IViewFactory factory) throws Exception{        
        return factory.getView(gObj, length);        
    }
    
}

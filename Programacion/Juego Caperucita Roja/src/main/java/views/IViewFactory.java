
package views;

import game.IGameObject;


public interface IViewFactory {
   IAWTGameView getView(IGameObject gObj, int length) throws Exception; 
}

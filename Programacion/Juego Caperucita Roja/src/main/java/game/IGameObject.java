
package game;



public interface IGameObject {
        
    
    public Position getPosition();
    public void setPosition(Position position);  
    
   
    public Position moveToNextPosition();

   
    public int getValue();
    public void setValue(int value);
    
   
    public int getLifes();
    public void incLifes(int value);
    
   
    public void setGameMode(int mode);
}

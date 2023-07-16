
package game;

import org.json.JSONObject;


public class Blossom extends AbstractGameObject {
     
    
    public Blossom(){
    }
    
    public Blossom(Position position){
        super(position);
    }
    
    public Blossom(Position position, int value){
        super(position, value, 1);
    }
    
    public Blossom(Position position, int value, int life){
        super(position, value, life);
    }
    
    public Blossom(JSONObject obj){
        super(obj);
    }
    
    public void printBlossom(){
        System.out.println(this.toJSONObject());
    }
    
}

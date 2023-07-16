
package game;

import common.FileUtilities;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Stone extends AbstractGameObject {
    
    int dX, dY;

    Stone(Position position) {
        super(position);    
    }
    
    Stone(Position position, int value, int life ) {
        super(position, value, life);    
    }
    
    Stone(JSONObject jObj) throws JSONException {
        super(jObj);    
    } 
    
    
    public void printStone(){
        System.out.println(this.toJSONObject());
    }
    
   
}

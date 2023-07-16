
package game;

import static common.IToJsonObject.TypeLabel;
import org.json.JSONObject;


public class GameObjectsJSONFactory {
    
    public static IGameObject getGameObject(JSONObject jObj) {
        
        IGameObject gObj = null;
        
        String typeLabel = jObj.getString(TypeLabel);
        
        if (typeLabel.equals("Blossom")){
            gObj = new Blossom(jObj);
        }
        else if (typeLabel.equals("Spider")){
            gObj = new Spider(jObj);
        }
        else if (typeLabel.equals("Bee")){
            gObj = new Bee(jObj);
        }
        else if (typeLabel.equals("Fly")){
            gObj = new Fly(jObj);
        }
        else if (typeLabel.equals("Stone")){
            gObj = new Stone(jObj);
        } 
        else if (typeLabel.equals("RidingHood")){
            gObj = new RidingHood(jObj);
        }        
        return gObj;
    }

}

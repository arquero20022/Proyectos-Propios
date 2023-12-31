
package common;

import game.Position;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;


public class FileUtilities {
    
    
    public static void crearDirectorio(String pathname) throws SecurityException {
        
        File directorio = new File(pathname);        

        if (!directorio.exists()) {
            System.out.println("crearDirectorio: directorio ha creado en " + pathname);
            directorio.mkdir();
        }
        else{
            System.out.println("crearDirectorio: directorio ya existe en " + pathname);
        }
    }

  
    public static void writeToFile(String s, String fichero) throws FileNotFoundException {

        if (s == null || fichero == null){
            return;
        }

        PrintWriter streamWriter;

        try {
            streamWriter = new PrintWriter(new FileOutputStream (fichero));
            streamWriter.println(s);
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
            throw e;
        }
    }

   
    public static void writeJsonsToFile(JSONObject [] jsons, String fileName) {

        PrintWriter streamWriter;
        
        // Test arguments.
        if (jsons == null || fileName == null){
            return;
        }

        // Write each json object in a new line.
        try {
            streamWriter = new PrintWriter(new FileOutputStream (fileName));
            for (JSONObject item : jsons) {
                streamWriter.println(item.toString());
            }
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
        }
    }



    public static JSONArray readJsonsFromFile(String fileName) {

        String line;
        BufferedReader fileReader;
        JSONArray jArray = new JSONArray(); 
        try{
            fileReader = new BufferedReader(new FileReader(fileName));

            while((line = fileReader.readLine()) != null){
                jArray.put(new JSONObject(line));
            }
            fileReader.close();            
        } catch(IOException ioe){
            jArray = null;
        } 
        return jArray;
    }  
    
    public static void main(String [] args){
        
        // Prueba creación directorrio.
        String dirName = "src/main/resources/jsons";
        crearDirectorio(dirName);
        crearDirectorio(dirName);
        
        // Prueba escritura en fichero.
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,1);
        Position p3 = new Position(1,1);
        Position p4 = new Position(1,2);
        Position pA1[] = {p1, p2, p3, p4};
        Position pA2[] = {p4, p3, p2, p1};       
        
        
        JSONObject jsons [] = {
            p1.toJSONObject(),
            p2.toJSONObject(),
            p3.toJSONObject(),
            p4.toJSONObject(),
            
        };
        writeJsonsToFile(jsons, dirName + "/testWriteFile");
        
        // Prueba Lectura
        JSONArray jArray = readJsonsFromFile(dirName + "/testWriteFile");
        
        for(int i = 0; i < jArray.length(); i++){
            System.out.println(jArray.get(i).toString());
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import common.FileUtilities;
import common.IToJsonObject;
import static common.IToJsonObject.TypeLabel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import org.json.JSONArray;
import org.json.JSONObject;
import views.IAWTGameView;
import views.boxes.VNumberedBox;
import views.boxes.VNumberedCircle;

/**
 *
 * @author juanangel
 */
public class GameEditor_3 extends JFrame implements KeyListener {
    
    public static final int UP_KEY    = 38;
    public static final int DOWN_KEY  = 40;
    public static final int RIGTH_KEY = 39;
    public static final int LEFT_KEY  = 37;
    
    public static final int CANVAS_WIDTH = 480;
    
    int boxSize = 40;
    int row, col;
    
    Canvas canvas;
    JPanel canvasFrame;
    JLabel positionLabel;
    
    // Declare new buttons
    JButton btAddClover, btAddDandelion, btAddSpider, btClear;
    
    // Declare array of IGameObject and objects counter
    IGameObject gameObjects [] = new IGameObject [4];
    int itemsCounter;
    
    // Declare File menu and Save and Load items
    String path = "src/main/resources/games/game.txt";
    JMenuBar menuBar;
    JMenu menuFile;
    JMenuItem itSave, itLoad;

    
    public GameEditor_3() throws Exception{

        super("Game Editor v3");
       
        ///////////////////////////////////////////////////////////////////////////////////
        // Define Buttos and buttons handlers.............................................
        btAddClover = new JButton("Clover");
        btAddClover.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Clover selected");
                    if (itemsCounter < gameObjects.length){
                        gameObjects[itemsCounter] = new Blossom(new Position(col, row), 10, 1);
                        itemsCounter++;
                        printGameItems();
                        canvas.drawGameItems(gameObjects);
                    }
                    else {
                        System.out.println("Clover can not be added: too many objects");
                    }
                    requestFocusInWindow();
                }
            }
        );
        
        btAddDandelion = new JButton("Dandelion");
        btAddDandelion.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Dandelion selected");
                    if (itemsCounter < gameObjects.length){
                        gameObjects[itemsCounter] = new Blossom(new Position(col, row), 5, 1);
                        itemsCounter++;
                        printGameItems(); 
                        canvas.drawGameItems(gameObjects);
                    }
                    else {
                        System.out.println("Dandelion can not be added: too many objects");
                    }
                    requestFocusInWindow();
                }
            }
        );
        
        btAddSpider = new JButton("Spider");
        btAddSpider.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Spider selected");
                    if (itemsCounter < gameObjects.length){
                        gameObjects[itemsCounter] = new Spider(new Position(col, row), 5, 1);
                        itemsCounter++;
                        printGameItems(); 
                        canvas.drawGameItems(gameObjects);
                    }
                    else {
                        System.out.println("Spider can not be added: too many objects");
                    }
                    requestFocusInWindow();
                }
            }
        );
        
        btClear = new JButton("Clear All");
        btClear.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Cleaning all.");
                    for (int i = 0; i < gameObjects.length; i++){
                        gameObjects[i] = null;
                        itemsCounter = 0;
                    }
                    printGameItems(); 
                    canvas.drawGameItems(gameObjects);
                    requestFocusInWindow();
                }
            }
        );
        
        /////////////////////////////////////////////////////////////////////////////////
        // Define menu and menu items and menu handlers.
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        itSave = new JMenuItem("Save");
        itLoad = new JMenuItem("Load");
               
        itSave.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Saving objects");
                    if (gameObjects != null){
                        JSONObject jObjs [] = new JSONObject[gameObjects.length];
                        for(int i = 0; i < jObjs.length; i++){
                            jObjs[i] = ((IToJsonObject)gameObjects[i]).toJSONObject();
                        }
                        FileUtilities.writeJsonsToFile(jObjs, path);
                    }
                    requestFocusInWindow();
                }
            }
        );
        
        itLoad.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    System.out.println("Loading objects");
                    JSONArray jArray = FileUtilities.readJsonsFromFile(path);
                    if (jArray != null){
                        gameObjects = new IGameObject [jArray.length()];
                        for (int i = 0; i < gameObjects.length; i++){
                            JSONObject jObj = jArray.getJSONObject(i);
                            String typeLabel = jObj.getString(TypeLabel);
                            if (typeLabel.equals("Blossom")){
                                gameObjects[i] = new Blossom(jObj);
                            }
                            else if (typeLabel.equals("Spider")){
                                gameObjects[i] = new Spider(jObj);
                            }
                        }
                        printGameItems(); 
                        canvas.drawGameItems(gameObjects);
                    }
                    requestFocusInWindow();
                }
            }
        );
        
        /////////////////////////////////////////////////////////////////////////////////
        // Add position label and buttons to the window.
            
        positionLabel = new JLabel("[" + col + ", " + row + "]");
        positionLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); 
        positionLabel.setPreferredSize(new Dimension(120,40));
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel pnControls = new JPanel();
        pnControls.setLayout(new GridLayout(2,1));
        JPanel pnButtons = new JPanel();
        pnButtons.add(btAddClover);
        pnButtons.add(btAddDandelion);
        pnButtons.add(btAddSpider);
        pnButtons.add(btClear);

        
        pnControls.add(positionLabel);
        pnControls.add(pnButtons);
        
        canvas = new Canvas(CANVAS_WIDTH, boxSize);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_WIDTH));
        canvas.setBorder(BorderFactory.createLineBorder(Color.blue));
       
        canvasFrame = new JPanel();
        canvasFrame.setPreferredSize(new Dimension(CANVAS_WIDTH + 40, CANVAS_WIDTH + 40));
        canvasFrame.add(canvas);
        getContentPane().add(canvasFrame);
        getContentPane().add(pnControls, BorderLayout.SOUTH);
       
        menuFile.add(itSave);
        menuFile.add(itLoad);
        menuBar.add(menuFile);
        menuBar.setBorder(BorderFactory.createLineBorder(Color.blue));
        setJMenuBar(menuBar);             
        
        setSize (CANVAS_WIDTH + 40, CANVAS_WIDTH + 160);
        setResizable(false);
        setVisible(true);         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
       
        addKeyListener(this);
        System.out.println(this.getFocusableWindowState());
        this.setFocusable(true);
    }
    
    private void printGameItems(){
        System.out.println("Objects Added to Game are: ");
        for (int i = 0; i < itemsCounter; i++){
            System.out.println( ( (IToJsonObject) gameObjects[i]).toJSONObject());
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    // Version 1
    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        System.out.println("code --> " + tecla);
        switch (tecla) {
            case UP_KEY:  
                System.out.println("UP_KEY");
                row--;
                break;
            case DOWN_KEY:
                System.out.println("DOWN_KEY");
                row++;                    
                break;
            case RIGTH_KEY:
                System.out.println("RIGTH_KEY");
                col++;
                break;
            case LEFT_KEY:
                System.out.println("LEFT_KEY");
                col--;
                break; 
        }
        positionLabel.setText("[" + col + ", " + row + "]");
        setInLimits();
        canvas.setSquareCoordinates(col, row);  
    }
    
    private void setInLimits(){
        
        int lastBox = (CANVAS_WIDTH/boxSize) - 1;
        
        if (col < 0){
            col = 0;
        }
        else if ( col > lastBox ){
            col = lastBox;
        }
        
        if (row < 0){
            row = 0;
        }
        else if ( row > lastBox){
            row = lastBox;
        } 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    public static void main(String [] args) throws Exception{
       GameEditor_3 gui = new GameEditor_3();
    }


    class Canvas extends JPanel {

        int size, boxSize;
        int pX, pY;
        
        IGameObject objects[];

        public Canvas(int size, int boxSize){
            this.size = size;
            this.boxSize = boxSize;
        }

        public void setSquareCoordinates(int x, int y){
            pX = x;
            pY = y;
            repaint();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);   
            drawGrid(g);
            //g.setColor(Color.red);
            //g.fillRect(pX*boxSize+4, pY*boxSize+4, boxSize-8, boxSize-8);
            drawSquare(g, pX, pY);
            try {
                drawGameItems(g);
            } catch (Exception ex) {
                Logger.getLogger(GameEditor_3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     

        private void drawGrid(Graphics g){
            Color c = g.getColor();
            g.setColor(Color.LIGHT_GRAY);
            int nLines = size/boxSize;
            System.out.println("---- " + nLines);
            for (int i = 1; i < nLines; i++){
               g.drawLine(i*boxSize, 0, i*boxSize, size);
               g.drawLine(0, i*boxSize, size, i*boxSize);
            } 
            g.setColor(c);
        }

        private void drawSquare(Graphics g, int xCoord, int yCoord) {

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(xCoord*boxSize-2, yCoord*boxSize-2, boxSize+4, boxSize+4);
        }
        
        public void drawGameItems(IGameObject [] objs){
            this.objects = objs;
            repaint();
        }
        
        private void drawGameItems(Graphics g) throws Exception{
            
            IAWTGameView view = null;
            
            if (objects != null){
                for (int i = 0; i < objects.length; i++){
                    if (objects[i] != null){
                        if (objects[i] instanceof Blossom){
                            if (objects[i].getValue() >= 10) {
                               view = new VNumberedBox(objects[i], boxSize, Color.GREEN, "Clover");
                            }
                            else {
                                view = new VNumberedBox(objects[i], boxSize, Color.pink, "DLion");
                            }
                        }
                        else if (objects[i] instanceof Spider){
                            view = new VNumberedCircle(objects[i], boxSize, Color.black, "Spider");
                        }
                        view.draw(g);
                    }
                }
            }
        }
    }
}
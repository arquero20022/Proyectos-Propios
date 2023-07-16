package game;

import common.FileUtilities;
import common.IToJsonObject;

import static common.IToJsonObject.TypeLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sound.sampled.*;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game extends JFrame implements KeyListener, ActionListener {

    public static final int UP_KEY = 38;
    public static final int DOWN_KEY = 40;
    public static final int RIGTH_KEY = 39;
    public static final int LEFT_KEY = 37;
    public static final int SPACE_KEY = 32;
    int lastKey = DOWN_KEY;

    public static int CANVAS_WIDTH = 480;
    int boxSize = 40;
    int row, col;
    GameCanvas canvas;
    JPanel canvasFrame;
    JLabel dataLabel;

    int screenCounter = 0;
    int pantallaGuardada;
    int nivelesPasados = 1;
    int numBichos = 1;
    int restoBichos = 0;
    int mitadTiempo = 0;

    Timer timer;
    int tick = 600;

    int auto = 0;

    ConcurrentLinkedQueue<IGameObject> gObjs = new ConcurrentLinkedQueue<IGameObject>();

   
    RidingHood ridingHood = new RidingHood(new Position(0, 0), 1, 1, gObjs);

    
    JMenuBar menuBar;
    JMenu menuVistas, menuCarga;
    JMenuItem itColores, itFiguras, itGeo, itCarga, itGuarda;

   
    Clip beep;
    Clip musica;
    Clip death;

    
    public Game(int boxSize, int auto) throws Exception {

        super("Caperucita Roja");

        this.boxSize = boxSize;
        this.auto = auto;

        
        gObjs.add(ridingHood);
        loadNewBoard(0);

        beep = AudioSystem.getClip();
        beep.open(AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/beep.wav")));

        musica = AudioSystem.getClip();
        musica.open(AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/musicaJuego.wav")));
        musica.setFramePosition(0);
        musica.loop(Clip.LOOP_CONTINUOUSLY);

        death = AudioSystem.getClip();
        death.open(AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/death.wav")));

    
        dataLabel = new JLabel(ridingHood.toString());
        dataLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        dataLabel.setPreferredSize(new Dimension(120, 40));
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);

        canvas = new GameCanvas(CANVAS_WIDTH, boxSize);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_WIDTH));
        canvas.setBorder(BorderFactory.createLineBorder(Color.blue));

        canvasFrame = new JPanel();
        canvasFrame.setPreferredSize(new Dimension(CANVAS_WIDTH + 40, CANVAS_WIDTH + 40));
        canvasFrame.add(canvas);
        getContentPane().add(canvasFrame);
        getContentPane().add(dataLabel, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        menuVistas = new JMenu("Vistas");
        itColores = new JMenuItem("Colores");
        itFiguras = new JMenuItem("Figuras");
        itGeo = new JMenuItem("Geometria");
        menuCarga = new JMenu("Archivo");
        itGuarda = new JMenuItem("Guardar");
        itCarga = new JMenuItem("Cargar");

        itColores.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                GameCanvas.setVistas(1);
                requestFocusInWindow();
            }
        }
        );

        itFiguras.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                GameCanvas.setVistas(2);
                requestFocusInWindow();
            }
        }
        );

        itGeo.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                GameCanvas.setVistas(3);
                requestFocusInWindow();
            }
        }
        );

        itGuarda.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                timer.stop();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("src/main/resources/games/saves"));
                int seleccion = fileChooser.showSaveDialog(canvas);
                File fichero = fileChooser.getSelectedFile();
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Guardando objetos...");
                    if (gObjs != null) {
                        JSONObject jObjs[] = new JSONObject[gObjs.size()];
                        int i = 0;
                        for (IGameObject igo : gObjs) {
                            jObjs[i++] = ((IToJsonObject) igo).toJSONObject();
                        }
                        FileUtilities.writeJsonsToFile(jObjs, fichero.getPath() + ".txt");
                    }
                    requestFocusInWindow();
                }
            }
        }
        );

        itCarga.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Nivel CARGADO: " + screenCounter);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("src/main/resources/games/saves"));
                int seleccion = fileChooser.showOpenDialog(canvas);
                File fichero = fileChooser.getSelectedFile();
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Cargando objetos...");
                    JSONArray jArray = FileUtilities.readJsonsFromFile(fichero.toString());
                    if (jArray != null) {
                        gObjs = new ConcurrentLinkedQueue<IGameObject>();
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            String typeLabel = jObj.getString(TypeLabel);
                            int mismoSitio = 0;
                            // Si el objeto es una instancia de Bee, creamos un Bee y lo metemos en la lista
                            for (int j = 0; j < jArray.length(); j++) {
                                JSONObject jObj2 = jArray.getJSONObject(j);

                                if (GameObjectsJSONFactory.getGameObject(jObj).getPosition().toString().equals(GameObjectsJSONFactory.getGameObject(jObj2).getPosition().toString()) && jObj != jObj2 ) {
                                    mismoSitio = 1;
                                    jArray.remove(i);
                                    break;
                                }
                            }

                            if (mismoSitio == 0) {
                                if (GameObjectsJSONFactory.getGameObject(jObj) instanceof Bee) {
                                    // Colocamos screenCounter a 2 para que la siguiente pantalla sea la 3 (ya que 
                                    // tenemos que seguir el orden de Fly, Bee, Spider)
                                    screenCounter = 2;
                                    // Generamos una Bee en 0,0 con los valores que obtenemos del JSON
                                    gObjs.add(new Bee(GameObjectsJSONFactory.getGameObject(jObj).getPosition(), GameObjectsJSONFactory.getGameObject(jObj).getValue(), GameObjectsJSONFactory.getGameObject(jObj).getLifes(), gObjs));
                                } // Si el objeto es una instancia de Fly, ver explicación en Bee
                                else if (GameObjectsJSONFactory.getGameObject(jObj) instanceof Fly) {
                                    screenCounter = 1;
                                    gObjs.add(new Fly(GameObjectsJSONFactory.getGameObject(jObj).getPosition(), GameObjectsJSONFactory.getGameObject(jObj).getValue(), GameObjectsJSONFactory.getGameObject(jObj).getLifes(), gObjs));
                                } 
                                else if (GameObjectsJSONFactory.getGameObject(jObj) instanceof Spider) {
                                    screenCounter = 3;
                                    gObjs.add(new Spider(GameObjectsJSONFactory.getGameObject(jObj).getPosition(), GameObjectsJSONFactory.getGameObject(jObj).getValue(), GameObjectsJSONFactory.getGameObject(jObj).getLifes(), gObjs));

                                } 
                                else if (GameObjectsJSONFactory.getGameObject(jObj) instanceof Stone) {
                                    gObjs.add(new Stone(GameObjectsJSONFactory.getGameObject(jObj).getPosition(), GameObjectsJSONFactory.getGameObject(jObj).getValue(), GameObjectsJSONFactory.getGameObject(jObj).getLifes()));
                                } 
                                else if (GameObjectsJSONFactory.getGameObject(jObj) instanceof RidingHood) {
                                    ridingHood = new RidingHood(new Position(0, 0), GameObjectsJSONFactory.getGameObject(jObj).getValue(), GameObjectsJSONFactory.getGameObject(jObj).getLifes(), gObjs);
                                    ridingHood.setPosition(GameObjectsJSONFactory.getGameObject(jObj).getPosition());
                                    gObjs.add(ridingHood);
                                } 
                                else {
                                    gObjs.add(GameObjectsJSONFactory.getGameObject(jObj));
                                }
                                
                            }
                            mismoSitio=0;
                        }
                    }
                    
                    printGameItems();
                 
                    canvas.drawObjects(gObjs);
                    restoBichos = numResiduo();
                }
                requestFocusInWindow();
            }

        }
        );

       
        menuVistas.add(itColores);

        menuVistas.add(itFiguras);

        menuVistas.add(itGeo);

        menuCarga.add(itCarga);

        menuCarga.add(itGuarda);

        menuBar.add(menuVistas);

        menuBar.add(menuCarga);

        menuBar.setBorder(BorderFactory.createLineBorder(Color.blue));
        setJMenuBar(menuBar);

        setSize(CANVAS_WIDTH
                + 40, CANVAS_WIDTH + 110);
        setResizable(
                false);
        setVisible(
                true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(
                this);

        this.setFocusable(
                true);
        timer = new Timer(tick, this);

    }

    public void keyTyped(KeyEvent ke) {
    }

    
    public void keyPressed(KeyEvent ke) {
        lastKey = ke.getKeyCode();
        if (lastKey == SPACE_KEY) {
            if (timer.isRunning()) {
                timer.stop();
                musica.stop();
            } else {
                timer.start();
                musica.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void keyReleased(KeyEvent ke) {
    }

    public void actionPerformed(ActionEvent ae) {

       
        setDirection(lastKey);

    
        ridingHood.moveToNextPosition();

        
        if (screenCounter == 1) {
            for (IGameObject fly : gObjs) {
                if (fly instanceof Fly) {
                    fly.moveToNextPosition();
                }
            }
        }
        if (screenCounter == 2) {
            for (IGameObject bee : gObjs) {
                if (bee instanceof Bee) {
                    bee.moveToNextPosition();
                }
            }
        }
        if (screenCounter == 3) {
            if (mitadTiempo == 1) {
                for (IGameObject spider : gObjs) {
                    if (spider instanceof Spider) {
                        spider.moveToNextPosition();
                    }
                }
                mitadTiempo = 0;
            } else {
                mitadTiempo = 1;
            }
        }

        
        setInLimits();
        setInLimitsFly();
        noPuedesPasar();

       
        try {
            Fin();
        } catch (LineUnavailableException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            
            e.printStackTrace();
        }

      
        PuntosCaperucita();

        
        if (comprobacionCelda() <= restoBichos + 1) {
            screenCounter++;
            ridingHood.incLifes(1);
            loadNewBoard(screenCounter);
        }

        
        dataLabel.setText(ridingHood.toString());
        canvas.drawObjects(gObjs);
    }

    
    private int comprobacionCelda() {
        Position rhPos = ridingHood.getPosition();
        for (IGameObject gObj : gObjs) {
            // Si el objeto no es ni ridinhood ni una instancia de Bee, ni una de Spider, ni de Fly, ni de Stone, y la posicion de ridinghood es igual a la del objeto
            if (gObj != ridingHood && !(gObj instanceof Bee) && !(gObj instanceof Spider) && !(gObj instanceof Fly) && !(gObj instanceof Stone) && rhPos.isEqual(gObj.getPosition())) {
                // Sumamos el valor del objeto al del objeto.
                int valor = ridingHood.getValue() + gObj.getValue();
                ridingHood.setValue(valor);
                beep.stop();
                beep.setFramePosition(0);
                beep.start();
                gObjs.remove(gObj);
            } else if (!(gObj instanceof Bee) && !(gObj instanceof Spider) && !(gObj instanceof Fly) && !(gObj instanceof Stone)) {
                for (IGameObject bee : gObjs) {
                    if (bee instanceof Bee) {
                        Position bePos = ((Bee) bee).getPosition();
                        if (bePos.isEqual(rhPos)) {
                            ridingHood.setValue(ridingHood.getValue() - 5);
                            System.out.println("Has chocado contra una abeja. -5 puntos");
                            death.stop();
                            death.setFramePosition(0);
                            death.start();
                        } else if (bePos.isEqual(gObj.getPosition())) {
                            gObjs.remove(gObj);
                        }
                    }
                }
            } else if ((gObj instanceof Spider) && !(gObj instanceof Fly) && !(gObj instanceof Stone)) {
                for (IGameObject spider : gObjs) {
                    if (spider instanceof Spider) {
                        Position spiPos = ((Spider) spider).getPosition();
                        if (spiPos.isEqual(rhPos)) {
                            if (numBichos == 5) {
                                ridingHood.setLifes(ridingHood.getLifes() - 2);
                            } else {
                                ridingHood.setLifes(ridingHood.getLifes() - 1);
                            }
                            System.out.println("Una araña te ha pillado. -1 vida");
                            death.stop();
                            death.setFramePosition(0);
                            death.start();
                            gObjs.remove(spider);
                        }
                    }
                }

            } else if ((gObj instanceof Fly)) {
                for (IGameObject fly : gObjs) {
                    if (fly instanceof Fly) {
                        Position flyPos = ((Fly) fly).getPosition();
                        if (flyPos.isEqual(rhPos)) {
                            ridingHood.setValue(ridingHood.getValue() - 10);
                            System.out.println("Una mosca te ha pillado. -10 puntos");
                            death.stop();
                            death.setFramePosition(0);
                            death.start();
                            gObjs.remove(fly);
                        }
                    }
                }

            }
        }
        return gObjs.size();
    }

   
    private void setDirection(int lastKey) {
        switch (lastKey) {
            case UP_KEY:
                ridingHood.moveUp();
                break;
            case DOWN_KEY:
                ridingHood.moveDown();
                break;
            case RIGTH_KEY:
                ridingHood.moveRigth();
                break;
            case LEFT_KEY:
                ridingHood.moveLeft();
                break;
        }
    }

    
    private void noPuedesPasar() {
        for (IGameObject stone : gObjs) {
            if (stone instanceof Stone) {
                if (ridingHood.getPosition().getX() == stone.getPosition().getX() && ridingHood.getPosition().getY() == stone.getPosition().getY()) {
                    if (lastKey == DOWN_KEY) {
                        ridingHood.position.y = stone.getPosition().getY() - 1;
                    } else if (lastKey == UP_KEY) {
                        ridingHood.position.y = stone.getPosition().getY() + 1;
                    }
                    if (lastKey == RIGTH_KEY) {
                        ridingHood.position.x = stone.getPosition().getX() - 1;
                    } else if (lastKey == LEFT_KEY) {
                        ridingHood.position.x = stone.getPosition().getX() + 1;
                    }
                }
            }
        }
    }

    
    private void setInLimits() {

        int lastBox = (CANVAS_WIDTH / boxSize) - 1;

        if (ridingHood.getPosition().getX() < 0) {
            ridingHood.position.x = 0;
        } else if (ridingHood.getPosition().getX() > lastBox) {
            ridingHood.position.x = lastBox;
        }

        if (ridingHood.getPosition().getY() < 0) {
            ridingHood.position.y = 0;
        } else if (ridingHood.getPosition().getY() > lastBox) {
            ridingHood.position.y = lastBox;
        }
    }

    /*
    Comprueba que la mosca no se sale del tablero.
    En caso contrario corrige su posición
     */
    private void setInLimitsFly() {
        int lastBox = (CANVAS_WIDTH / boxSize) - 1;
        for (IGameObject fly : gObjs) {
            if (fly instanceof Fly) {
                if (fly.getPosition().getX() < 0) {
                    ((Fly) fly).position.x = 0;
                } else if (fly.getPosition().getX() > lastBox) {
                    ((Fly) fly).position.x = lastBox;
                }
                if (fly.getPosition().getY() < 0) {
                    ((Fly) fly).position.y = 0;
                } else if (fly.getPosition().getY() > lastBox) {
                    ((Fly) fly).position.y = lastBox;
                }
            }
        }
    }

    public void Fin() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if (ridingHood.getLifes() <= 0) {
            timer.stop();
            musica.stop();
            GameOver go = new GameOver(ridingHood.getValue(), nivelesPasados);
            go.setVisible(true);
            dispose();
        }
    }

    public void PuntosCaperucita() {
        if (ridingHood.getValue() <= 0) {
            ridingHood.setValue(0);
        }
    }

 

    private void loadNewBoard(int counter) {
        switch (counter) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    gObjs.add(new Blossom(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), (int) (Math.random() * 20 + 1), 4));
                }
                nivelesPasados++;
                break;
            case 1:
                String path = "src/main/resources/games/nivel1.txt";
                gObjs.removeAll(gObjs);
                gObjs.add(ridingHood);
                System.out.println("------- NUEVO NIVEL 1 ------- ");
                System.out.println("Contador de pantallas: " + screenCounter);
                System.out.println("Cargando objetos...");
                JSONArray jArray = FileUtilities.readJsonsFromFile(path);
                if (jArray != null) {
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        String typeLabel = jObj.getString(TypeLabel);
                    
                        if (GameObjectsJSONFactory.getGameObject(jObj).getPosition().getX() < CANVAS_WIDTH / boxSize && GameObjectsJSONFactory.getGameObject(jObj).getPosition().getY() < CANVAS_WIDTH / boxSize) {
                            gObjs.add(GameObjectsJSONFactory.getGameObject(jObj));
                        }
                    }
                }
                
                for (int i = 0; i < nivelesPasados; i++) {
                    gObjs.add(new Blossom(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), (int) (Math.random() * 20 + 1), 4));
                }
                for (int i = 0; i <= numBichos; i++) {
                    gObjs.add(new Fly(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1, gObjs));
                    gObjs.add(new Stone(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1));

                }
                System.out.println("--------------- NIVEL 1--------------- ");
                System.out.println(nivelesPasados + " objetos generados aleatoriamente");
                restoBichos = numResiduo();
                System.out.println("Hay " + restoBichos + " que no son BLOSSOMS.");
                nivelesPasados++;
                break;

            case 2:
                String path1 = "src/main/resources/games/nivel2.txt";
                gObjs.removeAll(gObjs);
                gObjs.add(ridingHood);
                System.out.println("--------------- NIVEL 2---------------");
                System.out.println("Contador de pantallas: " + screenCounter);
                System.out.println("Cargando objetos...");
                JSONArray jArray1 = FileUtilities.readJsonsFromFile(path1);
               
                if (jArray1 != null) {
                    for (int i = 0; i < jArray1.length(); i++) {
                        JSONObject jObj = jArray1.getJSONObject(i);
                        String typeLabel = jObj.getString(TypeLabel);
                        if (GameObjectsJSONFactory.getGameObject(jObj).getPosition().getX() < CANVAS_WIDTH / boxSize && GameObjectsJSONFactory.getGameObject(jObj).getPosition().getY() < CANVAS_WIDTH / boxSize) {
                            gObjs.add(GameObjectsJSONFactory.getGameObject(jObj));
                        }
                    }
                }
                for (int i = 0; i < nivelesPasados; i++) {
                    gObjs.add(new Blossom(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), (int) (Math.random() * 20 + 1), 4));
                }
                for (int i = 0; i <= numBichos; i++) {
                    gObjs.add(new Bee(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1, gObjs));
                    gObjs.add(new Stone(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1));
                }
                System.out.println("--------------- NIVEL 2---------------");
                System.out.println(nivelesPasados + " objetos generados aleatoriamente");
                restoBichos = numResiduo();
                System.out.println("Hay " + restoBichos + " que no son BLOSSOMS.");
                nivelesPasados++;
                break;
            case 3:
                String path2 = "src/main/resources/games/nivel3.txt";
                gObjs.removeAll(gObjs);
                gObjs.add(ridingHood);
                System.out.println("--------------- NIVEL 3---------------");
                System.out.println("Contador de pantallas: " + screenCounter);
                System.out.println("Cargando objetos...");
                JSONArray jArray2 = FileUtilities.readJsonsFromFile(path2);
                
                if (jArray2 != null) {
                    for (int i = 0; i < jArray2.length(); i++) {
                        JSONObject jObj = jArray2.getJSONObject(i);
                        String typeLabel = jObj.getString(TypeLabel);
                        if (GameObjectsJSONFactory.getGameObject(jObj).getPosition().getX() < CANVAS_WIDTH / boxSize && GameObjectsJSONFactory.getGameObject(jObj).getPosition().getY() < CANVAS_WIDTH / boxSize) {
                            gObjs.add(GameObjectsJSONFactory.getGameObject(jObj));
                        }
                    }
                }
                for (int i = 0; i < nivelesPasados; i++) {
                    gObjs.add(new Blossom(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), (int) (Math.random() * 20 + 1), 4));
                }
                for (int i = 0; i <= numBichos; i++) {
                    gObjs.add(new Spider(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1, gObjs));
                    gObjs.add(new Stone(getRandomPosition(CANVAS_WIDTH / boxSize, CANVAS_WIDTH / boxSize), 4, 1));

                }
                System.out.println("--------------- NIVEL 3---------------");
                System.out.println(nivelesPasados + " objetos generados aleatoriamente");
                restoBichos = numResiduo();
                System.out.println("Hay " + restoBichos + " que no son buenos.");
                nivelesPasados++;
                numBichos++;
                if (numBichos == 5) {
                    numBichos = 5;
                }
                break;
            default:
                screenCounter = 0;
        }
    }

   
   

    private int numResiduo() {
        int num = gObjs.size();
        for (IGameObject igo : gObjs) {
            if (igo instanceof Blossom) {
                num--;
            }
        }
        return num;
    }
    

    
    public Position getRandomPosition(int mX, int mY) {
        int x = (int) (mX * Math.random());
        int y = (int) (mY * Math.random());
        return new Position(x, y);
    }

    public static void main(String[] args) throws Exception {
        MenuPrincipal m = new MenuPrincipal();
        m.setVisible(true);
    }
    
     private void printGameItems() {
        System.out.println("Los objetos añadidos al juego son: ");
        for (IGameObject obj : gObjs) {
            System.out.println(((IToJsonObject) obj).toJSONObject());
        }
    }
    
}

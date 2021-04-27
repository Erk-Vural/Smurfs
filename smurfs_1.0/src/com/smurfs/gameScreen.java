package com.smurfs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class gameScreen extends JPanel implements Runnable, KeyListener {
    private static final int tileSize = 80;
    public static final int WIDTH = 13 * tileSize;
    public static final int HEIGHT = 12 * tileSize;

    private Thread thread;
    private Graphics2D g;
    private BufferedImage image;

    private int FPS = 30;
    private int targetTime = 1000 / FPS;

    private Timer goldTimer;
    private Timer mushroomTimer;
    private TimerTask goldSpawn;
    private TimerTask mushroomSpawn;

    private boolean running;
    private boolean gameWin;
    private boolean isMoved;

    private ReadFile readFile;
    private Map map;
    private Player player;
    private Gold gold;
    private Mushroom mushroom;
    private Enemy enemy;
    private Enemy enemy2;
    int enemy_1;
    int enemy_2;

    int eRow;
    int eCol;
    int e2Row;
    int e2Col;

    public gameScreen() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime;
        long urdTime;
        long waitTime;

        while(running) {
            startTime = System.nanoTime();

            update();
            render();
            draw();
            gameStatus();

            urdTime = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - urdTime;

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void init() throws IOException {
        running = true;
        gameWin = false;
        isMoved = false;

        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)image.getGraphics();

        readFile = new ReadFile("harita.txt");
        map = new Map(tileSize);

        if(Menu.getChoice() == 1 ){ // TembelSirin
            player = new TembelSirin(5,6,map,
                    1, "Erk", "tembel");
        }else if((Menu.getChoice() == 2 )){ // GozlukluSirin
            player = new GozlukluSirin(5,6,map,
                    2, "Eda", "gozluklu");
        }

        int [][] enemyInfo = readFile.getEnemyInfo();
        enemy_1 = enemyInfo[0][0];
        switch (enemy_1) {
            case 11 -> {
                eRow = 0;
                eCol = 3;
                enemy = new Gargamel(eRow, eCol, map, player, 1, "Gargamel", "gargamel");
            }
            case 12 -> {
                eRow = 0;
                eCol = 10;
                enemy = new Gargamel(eRow, eCol, map, player, 1, "Gargamel", "gargamel");
            }
            case 13 -> {
                eRow = 5;
                eCol = 0;
                enemy = new Gargamel(eRow, eCol, map, player, 1, "Gargamel", "gargamel");
            }
            case 14 -> {
                eRow = 10;
                eCol = 3;
                enemy = new Gargamel(eRow, eCol, map, player, 1, "Gargamel", "gargamel");
            }
            case 21 -> {
                eRow = 0;
                eCol = 3;
                enemy = new Azman(eRow, eCol, map, player, 1, "Azman", "azman");
            }
            case 22 -> {
                eRow = 0;
                eCol = 10;
                enemy = new Azman(eRow, eCol, map, player, 1, "Azman", "azman");
            }
            case 23 -> {
                eRow = 5;
                eCol = 0;
                enemy = new Azman(eRow, eCol, map, player, 1, "Azman", "azman");
            }
            case 24 -> {
                eRow = 10;
                eCol = 3;
                enemy = new Azman(eRow, eCol, map, player, 1, "Azman", "azman");
            }
        }
        enemy_2 = enemyInfo[1][0];
        if(enemy_2 > 0) {
            switch (enemy_2) {
                case 11 -> {
                    e2Row = 0;
                    e2Col = 3;
                    enemy2 = new Gargamel(e2Row, e2Col, map, player, 2, "Gargamel", "gargamel");
                }
                case 12 -> {
                    e2Row = 0;
                    e2Col = 10;
                    enemy2 = new Gargamel(e2Row, e2Col, map, player, 2, "Gargamel", "gargamel");
                }
                case 13 -> {
                    e2Row = 5;
                    e2Col = 0;
                    enemy2 = new Gargamel(e2Row, e2Col, map, player, 2, "Gargamel", "gargamel");
                }
                case 14 -> {
                    e2Row = 10;
                    e2Col = 3;
                    enemy2 = new Gargamel(e2Row, e2Col, map, player, 2, "Gargamel", "gargamel");
                }
                case 21 -> {
                    e2Row = 0;
                    e2Col = 3;
                    enemy2 = new Azman(e2Row, e2Col, map, player, 2, "Azman", "azman");
                }
                case 22 -> {
                    e2Row = 0;
                    e2Col = 10;
                    enemy2 = new Azman(e2Row, e2Col, map, player, 2, "Azman", "azman");
                }
                case 23 -> {
                    e2Row = 5;
                    e2Col = 0;
                    enemy2 = new Azman(e2Row, e2Col, map, player, 2, "Azman", "azman");
                }
                case 24 -> {
                    e2Row = 10;
                    e2Col = 3;
                    enemy2 = new Azman(e2Row, e2Col, map, player, 2, "Azman", "azman");
                }
            }
        }
        gold = new Gold(map);
        mushroom = new Mushroom(map);
        spawnObjects();
    }

    private void gameStatus() {
        if(player.getRow() == 7 && player.getCol() == 12){
            gameWin = true;
        }
        if(player.getScore() > 0 && gameWin) {
            //You Win!
            GameOver GameOver = new GameOver(1, player.getScore());
            running = false;
        }
        else if(player.getScore() <= 0 && !gameWin) {
            //You Lost!
            GameOver GameOver = new GameOver(2, player.getScore());
            running = false;
        }
    }

    public  void drawBoard(Graphics2D g) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        if(player.getScore() <= 5) {
            g.setColor(Color.red);
        }else{
            g.setColor(Color.pink);
        }
        String s = player.showScore();
        g.drawString(s,900,930);
        if(isMoved){
            g.setColor(Color.black);
            g.fillRect(0,11*tileSize,tileSize*13,tileSize);
        }
        String st = "Player: " + player.getName();
        g.setColor(Color.pink);
        g.drawString(st,20,930);

        String enemy2Name = "";
        if(enemy_2 > 0) {
            enemy2Name = " & " + enemy2.getName();
        }

        String str = "Enemies: " + enemy.getName() + enemy2Name;
        g.setColor(Color.pink);
        g.drawString(str,370,930);
    }

    public void spawnObjects() {
        goldTimer = new Timer();

        goldSpawn = new TimerTask() {
            @Override
            public void run() {
                gold.setPoints();
            }
        };
        goldTimer.scheduleAtFixedRate(goldSpawn,
                gold.getSpawnTime() * 1000L,gold.getAppear() * 1000L);

        mushroomTimer = new Timer();
        mushroomSpawn = new TimerTask() {
                @Override
                public void run () {
                    mushroom.setPoints();
                }
        };
        mushroomTimer.scheduleAtFixedRate(mushroomSpawn,
                mushroom.getSpawnTime() * 1000L,mushroom.getAppear() * 1000L);
    }


    public void checkCollisions() {
        // Check gold
        int [][] gLoc = gold.getLocation();
        for(int i = 0; i < 5; i++) {
            if(player.getRow() == gLoc[i][0]
                    && player.getCol() == gLoc[i][1]) {
                gLoc[i][0] = -1;
                gLoc[i][1] = -1;
                gold.setLocation(gLoc);
                player.score += 5;
            }
        }

        // Check mushroom
        int [][] mLoc = mushroom.getLocation();
        for(int i = 0; i < 1; i++) {
            if(player.getRow() == mLoc[i][0]
                    && player.getCol() == mLoc[i][1]) {
                mLoc[i][0] = -1;
                mLoc[i][1] = -1;
                mushroom.setLocation(mLoc);
                player.score += 50;
            }
        }

        // Check enemy
        if(player.getRow() == enemy.getRow()
                && player.getCol() == enemy.getCol()) {
            enemy.setRow(eRow);
            enemy.setCol(eCol);
            if(enemy.getType().equals("gargamel")){
                if(player.getType().equals("gozluklu")){
                    player.score -= 15;
                }else if(player.getType().equals("tembel")){
                    player.score -= 10;
                }
            }else if(enemy.getType().equals("azman")){
                if(player.getType().equals("gozluklu")){
                    player.score -= 8;
                }else if(player.getType().equals("tembel")){
                    player.score -= 5;
                }
            }
        }

        // Check enemy2
        if(enemy_2 > 0) {
            if (player.getRow() == enemy2.getRow()
                    && player.getCol() == enemy2.getCol()) {
                enemy2.setRow(e2Row);
                enemy2.setCol(e2Col);
                if (enemy2.getType().equals("gargamel")) {
                    if (player.getType().equals("gozluklu")) {
                        player.score -= 15;
                    } else if (player.getType().equals("tembel")) {
                        player.score -= 10;
                    }
                } else if (enemy2.getType().equals("azman")) {
                    if (player.getType().equals("gozluklu")) {
                        player.score -= 8;
                    } else if (player.getType().equals("tembel")) {
                        player.score -= 5;
                    }
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void update() {
        map.update();
        player.update();
        gold.update();
        mushroom.update();
        enemy.update();
        if(enemy_2 > 0) {
            enemy2.update();
        }
    }
    private void render() {
        map.draw(g);
        drawBoard(g);
        player.draw(g);
        gold.draw(g);
        mushroom.draw(g);
        enemy.draw(g);
        if(enemy_2 > 0) {
            enemy2.draw(g);
        }

        g.setColor(Color.gray);
        g.fillRect(0,0,tileSize,tileSize);
    }

    private void draw() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0 , null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {

    }
    public void keyPressed(KeyEvent key) {
        int code = key.getKeyCode();

        if(code == KeyEvent.VK_LEFT) {
            if(player.getMoveSpeed() == 2){
                player.move(0,-1);
                checkCollisions();
            }
            player.move(0,-1);
        }
        if(code == KeyEvent.VK_RIGHT) {
            if(player.getMoveSpeed() == 2){
                player.move(0,+1);
                checkCollisions();
            }
            player.move(0,+1);
        }
        if(code == KeyEvent.VK_UP) {
            if(player.getMoveSpeed() == 2){
                player.move(-1,0);
                checkCollisions();
            }
            player.move(-1,0);
        }
        if(code == KeyEvent.VK_DOWN) {
            if(player.getMoveSpeed() == 2){
                player.move(1,0);
                checkCollisions();
            }
            player.move(1,0);
        }
        enemy.shortestPath();
        if(enemy_2 > 0) {
            enemy2.shortestPath();
        }
        checkCollisions();
        isMoved = true;
    }

    public void keyReleased(KeyEvent key) {
        enemy.moveEnemy(enemy.getPath(),false);
        if(enemy_2 > 0) {
            enemy2.moveEnemy(enemy2.getPath(),false);
        }
        checkCollisions();
        isMoved = false;
    }
}

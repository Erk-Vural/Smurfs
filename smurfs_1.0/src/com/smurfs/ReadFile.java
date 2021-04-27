package com.smurfs;

import java.io.*;
import java.util.Scanner;

public class ReadFile {
    Scanner scan;

    int rows;
    int columns;

    static int[][] map;

    int[][] enemyInfo;

    public static int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getEnemyInfo() {
        return enemyInfo;
    }

    public void setEnemyInfo(int[][] enemyInfo) {
        this.enemyInfo = enemyInfo;
    }

    public ReadFile(String scan) throws IOException {
        readMap(scan);
        readEnemy(scan);
    }

    public void readMap(String filename) {
        try {
            scan = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        rows = 11;
        columns = 13;
        map = new int[rows][columns];

        while(scan.hasNextLine()) {
            for(int i = 0; i < rows+1; i++) {
                String line = scan.nextLine().replaceAll("[^0-9.]", "");
                if(line == "") {
                    i = 0;
                    continue;
                }
                for(int j = 0; j < columns; j++) {
                    map[i-1][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }
        }
    }

    public void readEnemy(String filename) {
        try {
            scan = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        enemyInfo = new int[2][2];
        int i = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine().replaceAll("[^a-zA-Z]", "");
            while(!(line == "")) {
                if(line.substring(8, 16).equals("Gargamel")){
                    if(line.charAt(20) == 'A') {
                        enemyInfo[i][0] = 11;
                    }
                    else if(line.charAt(20) == 'B') {
                        enemyInfo[i][0] = 12;
                    }
                    else if(line.charAt(20) == 'C') {
                        enemyInfo[i][0] = 13;
                    }
                    else if(line.charAt(20) == 'D') {
                        enemyInfo[i][0] = 14;
                    }
                    i++;
                }
                else if(line.substring(8, 13).equals("Azman")) {
                    if(line.charAt(17) == 'A') {
                        enemyInfo[i][0] = 21;
                    }
                    else if(line.charAt(17) == 'B') {
                        enemyInfo[i][0] = 22;
                    }
                    else if(line.charAt(17) == 'C') {
                        enemyInfo[i][0] = 23;
                    }
                    else if(line.charAt(17) == 'D') {
                        enemyInfo[i][0] = 24;
                    }
                    i++;
                }
                line = scan.nextLine().replaceAll("[^a-zA-Z]", "");
            }
        }
    }
}

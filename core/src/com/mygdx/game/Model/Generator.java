package com.mygdx.game.Model;

import java.util.Random;

/**
 * Created by Erik on 2017-05-03.
 */
public class Generator {

    private int[][] mapArray;
    private final int row =20;
    private final int col = 40;
    private Random random = new Random();
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 3;


    public Generator() {
        this.mapArray = new int[row][col];
        setBasePoints();
        for (int x = 0; x < col - 1; x++) {
            growFromPoints(x);
        }
        placeGround();
    }

    public void setNextMapStructure(){
        int lastBasePoint = findRow(col - 1);
        clear(mapArray);
        for(int i = 0; i < row; i++){
            System.out.println();
            for(int j = 0; j < col; j++)
            {
                System.out.print(mapArray[i][j]);
            }
        }
        setBasePointsFrom(lastBasePoint);
        for (int x = 0; x < col - 1; x++) {
            growFromPoints(x);
        }
        placeGround();

        for(int i = 0; i < row; i++){
            System.out.println();
            for(int j = 0; j < col; j++)
            {
                System.out.print(mapArray[i][j]);
            }
        }
    }

    private void clear(int[][] array){
        for(int i = 0; i < col; i ++){
            for(int j = 0; j < row; j ++){
                array[j][i] = 0;
            }
        }
    }

    private void setBasePointsFrom(int lastBasePoint) {

        this.mapArray[lastBasePoint][0] = 1;

        for (int c = 2; c < col - pointsDistance; c = c + pointsDistance) {
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(col-1) == 0){
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][col-1] = 1;
        }
    }

    private void setBasePoints() {
        for (int c = 0; c < col - pointsDistance; c = c + pointsDistance) {
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(col-1) == 0){
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][col-1] = 1;
        }
    }

    private void growFromPoints(int initValue) {
        int endValue = nextPointValue(initValue);
        int rowStart = findRow(initValue);
        if (rowStart < endValue) {
            int growCord = random.nextInt(2) + findRow(initValue);

            this.mapArray[growCord][initValue + 1] = 1;
        } else if (rowStart > endValue) {
            int growCord = random.nextInt(2) * -1 + findRow(initValue);
            this.mapArray[growCord][initValue + 1] = 1;
        } else {
            this.mapArray[findRow(initValue)][initValue+1] =1;

        }
    }

    private int nextPointValue(int currentColum) {
        for (int c = currentColum + 1; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (this.mapArray[r][c] == 1) {
                    return r;
                }
            }
        }
        return 0;
    }

    private int findRow(int colum) {
        for (int x = 0; x < row; x++) {
            if (this.mapArray[x][colum] == 1)
                return x;
        }
        return 0;
    }
    private void placeGround(){
        for(int c = 0; c < col; c++){
            for(int r = row-1; r >= 0; r--){
                if(this.mapArray[r][c] == 1){
                    break;
                }
                mapArray[r][c] = 2;
            }
        }
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMapArray(int x, int y) {
        return this.mapArray[y][x];
    }

}
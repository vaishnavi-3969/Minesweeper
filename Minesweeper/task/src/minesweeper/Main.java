package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] game = new char[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                game[i][j]='.';
            }
        }
        System.out.println("How many mines do you want on the field?");
        int n = scanner.nextInt();
        int x,y;
        Random random = new Random();
        boolean flag;
        for(int i=0; i< n; i++){
            do{
                flag = false;
                x = random.nextInt(9);
                y = random.nextInt(9);
                if(game[x][y]!='X'){
                    game[x][y]='X';
                    flag = true;
                }
            }while(!flag);

        }
//        printArray(game);
        //logic for numbering
        //1. mine -> corner -> add 1 if it is .-> 3 cells
        //2. mine -> non-corner and at the sides -> 5 cells
        //3. if it is apart from that -> change 8 cells around it -> add 1
        //corner{
        String[][] state = new String[9][9];
//        for(int i=0; i<9; i++){
//            for(int j=0; j<9;j++){
//                if(game[i][j]=='X'){
//                    minesPresent[i][j]="Present";
//                }else{
//                    minesPresent[i][j]="Absent";
//                }
//            }
//        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if((i==0&&j==8) || (i==0&&j==0) || (i==8&&j==0) || (i==8&&j==8)){
                    state[i][j]="CORNER";
                } else if(i==0 || j==0 || i==8 || j==8){
                    state[i][j]="EDGES";
                } else {
                    state[i][j]="INSIDE";
                }
            }
        }
//        for(int i=0; i<9; i++){
//            for(int j=0; j<9;j++){
//                System.out.print(state[i][j]+ " ");
//            }
//            System.out.println();
//        }
        //corner case
        if(game[0][0]=='X'){
            game[1][1]=count(1,1,game);
            game[0][1]=count(0,1,game);
            game[1][0]=count(1,0,game);
        }
        if(game[0][8]=='X'){
            game[0][7]=count(0,7,game);
            game[1][8]=count(1,8,game);
            game[1][7]=count(1,7,game);
        }
        if(game[8][0]=='X'){
            game[8][1]=count(8,1,game);
            game[7][0]=count(7,0,game);
            game[7][1]=count(7,1,game);
        }
        if(game[8][8]=='X'){
            game[7][7]=count(7,7,game);
            game[7][8]=count(7,8,game);
            game[8][7]=count(8,7,game);
        }

        //edge case
        char c;
        //upper edge
        for(int j=1; j<8; j++){
            c=game[0][j];
            if(c=='X'){
                game[0][j-1]=count(0,j-1,game);
                game[1][j-1]=count(1,j-1,game);
                game[1][j]=count(1,j,game);
                game[1][j+1]=count(1,j+1,game);
                game[0][j+1]=count(0,j+1,game);
            }
        }
        //lower edge
        for(int j=1; j<8; j++){
            c=game[8][j];
            if(c=='X'){
                game[8][j-1]=count(8,j-1,game);
                game[7][j-1]=count(7,j-1,game);
                game[7][j]=count(7,j,game);
                game[7][j+1]=count(7,j+1,game);
                game[8][j+1]=count(8,j+1,game);
            }
        }
        //left edge
        for(int i=1; i<8; i++){
            c=game[i][0];
            if(c=='X'){
                game[i-1][0]=count(i-1,0,game);
                game[i-1][1]=count(i-1,1,game);
                game[i][1]=count(i,1,game);
                game[i+1][1]=count(i+1,1,game);
                game[i+1][0]=count(i+1,0,game);
            }
        }
        //right edge
        for(int i=1; i<8; i++){
            c=game[i][8];
            if(c=='X'){
                game[i-1][8]=count(i-1,8,game);
                game[i-1][7]=count(i-1,7,game);
                game[i][7]=count(i,7,game);
                game[i+1][7]=count(i+1,7,game);
                game[i+1][8]=count(i+1,8,game);
            }
        }

        //inside case
        for(int i=1; i<8; i++){
            for(int j=1; j<8; j++){
                c = game[i][j];
                if(c=='X'){
                    game[i-1][j-1]=count(i-1,j-1,game);
                    game[i-1][j]=count(i-1,j,game);
                    game[i-1][j+1]=count(i-1,j+1,game);
                    game[i][j+1]=count(i,j+1,game);
                    game[i+1][j+1]=count(i+1,j+1,game);
                    game[i+1][j]=count(i+1,j,game);
                    game[i+1][j-1]=count(i+1,j-1,game);
                    game[i][j-1]=count(i,j-1,game);
                }
            }
        }

//        System.out.println("Final array:");
    //printing array
    printArray(game);
    }
    //function for printing array
    public static void printArray(char[][] game){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(game[i][j]);
            }
            System.out.println();
        }
    }
    public static char count(int i, int j, char[][] game){
        char ret = game[i][j];
        if(ret=='X'){
            return ret;
        }else{
            char r = game[i][j];
            switch (r){
                case '.':
                    ret = '1';
                    break;
                case '1':
                    ret = '2';
                    break;
                case '2':
                    ret = '3';
                    break;
                case '3':
                    ret = '4';
                    break;
                case '4':
                    ret = '5';
                    break;
                case '5':
                    ret = '6';
                    break;
                case '6':
                    ret = '7';
                    break;
                case '7':
                    ret = '8';
                    break;
            }
        }
        return ret;
    }

}

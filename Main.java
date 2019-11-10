/*
Knight's tour
Warnsdorff's rule implemented by Mattias Ahle
mattias.ahle@gmail.com
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Timer start
        Instant start = Instant.now();

        Scanner input = new Scanner(System.in);
        Main myApp = new Main();
        Random random = new Random();
        Move move = new Move();
        float completed = 0;
        float uncompleted = 0;


        //How many times the Knight's tour should be iterated
        for (int i = 0; i < 10000; i++) {

            //Reset
            int numOfMoves = 0;
            move.resetVisited();

            //Generate random start coordinates
            int y = random.nextInt(8);
            int x = random.nextInt(8);

            //Print starting point
//            System.out.print("Start: ");
//            System.out.print(y);
//            System.out.println(" " + x);

            //Mark start coordinates as visited
            move.setVisited(y, x);

            //Prints that allow you to follow each move
            //Continue by pressing enter
//            myApp.printBoard(move.getVisited());
//            System.out.println();
//            while (input.nextLine() == null) {
//            }

            //Knight's tour loop
            do {
                //Find possible moves from current position
                boolean[][] possibleMoves = move.possibleMovesBoard(y, x); //possible moves on chessboard
                possibleMoves = move.possibleMovesVisited(possibleMoves);  //minus already visited

                //Check and pick which of the possible moves is the one
                //with the least possible moves in the next round
                int[] bestMoveCoordinate = move.bestMove(possibleMoves);

                //Perform move
                y = bestMoveCoordinate[0];
                x = bestMoveCoordinate[1];

                //If marker is not at last possible move do this:
                if (y != 9) {
                    move.setVisited(y, x); //Mark current coordinate as visited
                    numOfMoves++; //Increment move counter

                    //Prints that allow you to follow each move
                    //Continue by pressing enter
//                    myApp.printBoard(move.getVisited());
//                    System.out.println();
//                    while (input.nextLine() == null) {
//                    }
                }

                //Print every move including exit move
//                System.out.print("Move " + numOfMoves + ": ");
//                System.out.print(y);
//                System.out.println(" " + x);
            } while (y != 9);

            //Count number of complete Knight tours
            if (numOfMoves == 63) {
                completed++;
            } else {
                uncompleted++;
            }

            //Print number of moves for each Knight's tour
//            System.out.println("Number of moves: " + numOfMoves);
//            System.out.println();
        }

        //Timer stop
        Instant stop = Instant.now();

        //Print result
        System.out.println();
        System.out.println("Time elapsed = " + Duration.between(start, stop).toMillis() + "ms");
        float completedInPercent = (completed / (completed + uncompleted)) * 100;
        System.out.format("Completed tours = %.0f (%.2f%%)%n", completed, completedInPercent);
        System.out.format("Uncompleted tours = %.0f%n", uncompleted);
    }


    private void printBoard(boolean[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x]) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

}
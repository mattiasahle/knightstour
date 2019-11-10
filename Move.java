class Move {

    private boolean[][] visited;

    void resetVisited() {
        visited = new boolean[8][8];
    }

    void setVisited(int y, int x) {
        visited[y][x] = true;
    }

    boolean[][] getVisited() {
        return visited;
    }


    boolean[][] possibleMovesBoard(int y, int x) {
        boolean[][] possibleMoves = new boolean[8][8];
        int newY;
        int newX;

        //Move x (y;x)

        //Move 1 (1;2)
        if (y < 7 && x < 6) {
            newY = y + 1;
            newX = x + 2;
            possibleMoves[newY][newX] = true;
        }

        //Move 2 (2;1)
        if (y < 6 && x < 7) {
            newY = y + 2;
            newX = x + 1;
            possibleMoves[newY][newX] = true;
        }

        //Move 3 (2;-1)
        if (y < 6 && x > 0) {
            newY = y + 2;
            newX = x + -1;
            possibleMoves[newY][newX] = true;
        }

        //Move 4 (1;-2)
        if (y < 7 && x > 1) {
            newY = y + 1;
            newX = x + -2;
            possibleMoves[newY][newX] = true;
        }

        //Move 5 (-1;-2)
        if (y > 0 && x > 1) {
            newY = y + -1;
            newX = x + -2;
            possibleMoves[newY][newX] = true;
        }

        //Move 6 (-2;-1)
        if (y > 1 && x > 0) {
            newY = y + -2;
            newX = x + -1;
            possibleMoves[newY][newX] = true;
        }

        //Move 7 (-2;1)
        if (y > 1 && x < 7) {
            newY = y + -2;
            newX = x + 1;
            possibleMoves[newY][newX] = true;
        }

        //Move 8 (-1;2)
        if (y > 0 && x < 6) {
            newY = y + -1;
            newX = x + 2;
            possibleMoves[newY][newX] = true;
        }

        return possibleMoves;
    }


    boolean[][] possibleMovesVisited(boolean[][] possibleMoves) {

        for (int y = 0; y < possibleMoves.length; y++) {
            for (int x = 0; x < possibleMoves[y].length; x++) {
                if (possibleMoves[y][x] && visited[y][x]) {
                    possibleMoves[y][x] = false;
                }
            }
        }

        return possibleMoves;
    }


    int[] bestMove(boolean[][] possibleMoves) {
        int[] move = new int[2];
        int lowestNumOfPossibleMoves = 9;
        boolean noPossibleMoves = true;

        for (int y = 0; y < possibleMoves.length; y++) {
            for (int x = 0; x < possibleMoves[y].length; x++) {
                if (possibleMoves[y][x]) {
                    int numOfPossibleMoves = countPossibleMoves(y, x);
                    if (numOfPossibleMoves > 0 && numOfPossibleMoves < lowestNumOfPossibleMoves) {
                        lowestNumOfPossibleMoves = numOfPossibleMoves;
                        move[0] = y;
                        move[1] = x;
                        noPossibleMoves = false;
                    } else if (numOfPossibleMoves == 0) {
                        move[0] = y;
                        move[1] = x;
                        noPossibleMoves = false;
                    }
                }
            }
        }

        if (noPossibleMoves) {
            move[0] = 9;
        }

        return move;
    }


    private int countPossibleMoves(int y, int x) {
        int possibleMoves = 0;

        boolean[][] possibleMovesStep2 = possibleMovesBoard(y, x);
        possibleMovesStep2 = possibleMovesVisited(possibleMovesStep2);

        for (y = 0; y < possibleMovesStep2.length; y++) {
            for (x = 0; x < possibleMovesStep2[y].length; x++) {
                if (possibleMovesStep2[y][x]) {
                    possibleMoves++;
                }
            }
        }

        return possibleMoves;
    }

}
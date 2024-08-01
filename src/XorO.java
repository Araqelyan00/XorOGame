import java.util.Scanner;

public interface XorO {

    //    The games main code
    static void game() {
//        Create a scanner and input players names.
        Scanner sc = new Scanner(System.in);
        System.out.print("Player 1. Input your name: ");
        String name1 = sc.nextLine();
        System.out.print("Player 2. Input your name: ");
        String name2 = sc.nextLine();
//        Create a board
        char[][] board = new char[3][3];

//        Add dashes to board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        boolean isPlayer1 = true;

        boolean isFinished = false;

        while (!isFinished) {
            printBoard(board);

            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }

            if (isPlayer1) {
                System.out.println(name1 + "'s turn (X)");
            } else {
                System.out.println(name2 + "'s turn (O)");
            }

            int row = 0;
            int col = 0;

            while (true) {
                System.out.print("Enter a row(1, 2 or 3) : ");
                row = sc.nextInt();
                System.out.print("Enter a col(1, 2 or 3) : ");
                col = sc.nextInt();

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Invalid row or col!");
                } else if (board[row - 1][col - 1] != '-') {
                    System.out.println("This position is occupied!");
                } else {
                    break;
                }
            }

            board[row - 1][col - 1] = symbol;

            if (hasWon(board) == 'X') {
                System.out.println(name1 + " has won!");
                isFinished = true;
            } else if (hasWon(board) == 'O') {
                System.out.println(name2 + " has won!");
                isFinished = true;
            } else {
                if (nobodyWon(board)) {
                    System.out.println("Nobody has won!");
                    isFinished = true;
                } else {
                    isPlayer1 = !isPlayer1;
                }
            }
        }
        printBoard(board);
    }

    //    Method for printing our game's board
    static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0 || j == 1) {
                    System.out.print(board[i][j] + "  ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
    }

    //    Method to check who's won.
    static char hasWon(char[][] board) {
//        Row
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] != '-') {
                return board[i][0];
            }
        }
//        Col
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] != '-') {
                return board[0][i];
            }
        }
//        Diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] != '-') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] != '-') {
            return board[0][2];
        }

        return '-';
    }

    //    Method to check is drawn or not.
    static boolean nobodyWon(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}

import java.util.Scanner;
import doodlepad.*;

/**
 * Tester.java
 * 
 * @author Andrew Zhou
 * @since 10/20/23
 * @version 1.0.1
 *          This class is the main body of the program, displaying the menu for
 *          the user, printing the instructions of the different game modes, and
 *          housing the fundmental guessing mechanic of the Battleship Game.
 */

public class Tester {
  // Pretty much the main body of the program, runs the menu and guessing mechanic
  static int mode;
  public static void main(String[] args) {
    String m = "\n\n\n\n";
    while (true) {
      // Error handling
      try {
        drawGrid();
        Scanner start = new Scanner(System.in);
        System.out.println("Welcome to Battleship!\n");
        System.out.println(
            "Play a fast game if you are short on time or playing by yourself, otherwise play a regular game with a friend! \n");
        System.out.println("Type 0 for fast mode (-1 to also skip instructions) or Type 1 to pick regular mode");
        System.out.println("INSTRUCTIONS for each game mode will be shown after mode selection :)");

        mode = start.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Invalid Input: Please set mode equal to -1, 0, or, 1");
        System.out.println(m);
      }
    }

    // Using constructors to initialize
    Board opBoard = new Board(mode);
    Board guessBoard = new Board();

    // FAST MODE
    if (mode <= 0) {
      if (mode == 0) {
        System.out.println(m);
        // Instructions for fast mode
        System.out.println(
            "BATTLESHIP: FAST GAME INSTRUCTIONS: \n*Keep in mind, this is a variant of battleship and not exactly the original battleship game\n");
        System.out.println(
            "-Three ships composed of the dimensions (2 x 1), (3 x 1), (5 x 1) are randomly placed on an 8x8 board.\n");
        System.out.println(
            "-These ships completely lie on the board as contiguous blocks, are not diagonally placed, and do not share a point with one another.\n");
        System.out.println(
            "-The games ends in your victory if you destroy all of the generated ships by guessing their locations.");
        System.out.println(
            "-Guess one point of the grid (determined by X and Y) and input the x-coordinate and y-coordinate of your guess, and whether part of the enemy ship lies on that coordinate will be returned to you as feedback.\n");
        System.out.println(
            "-A 'hit' will be returned (with a 1 displayed on your grid) will show the presence of the enemy ship at that point, while a 'miss' will be returned (with a 2 on the grid) if the enemy ship is not present.\n");
        System.out.println(
            "-A ship is destroyed when every one of the points it occupies on the grid are destroyed by “hits” / the user’s guesses.\n");
        System.out.println(
            "Strive to minimize the number of your guesses, the lower the number, the greater the performance!\n");
      }
      // DRAW NEW BOARD with BUTTON

      System.out.println(m);
      guessBoard = new Board(8, 8);
      opBoard.generate();
      System.out.println("PLAYER 2: Guess the Ships\n\n");
    }

    // REGULAR MODE
    if (mode == 1) {
      System.out.println(m);
      // Instructions for Regular Game Mode
      System.out.println(
          "BATTLESHIP: REGULAR GAME INSTRUCTIONS \n\n-This game is a two player game, with one player setting up the puzzle/game, while the other person guesses.\n");
      System.out.println("Player 1 (The creator):\n");
      System.out.println(
          "-Player 1 configures the placement of 5 ships with the corresponding dimensions: (2 x 1), (3 x 1), (3 x 1), (4 x 1), and (5 x 1) on a 10x10 grid.\n");
      System.out.println(
          "-A configuration of ships is only valid when no ships occupy the same coordinates, lie completely on the grid, and lie perpendicular to the edges of the board (i.e lie straight, not diagonally).\n");
      System.out.println(
          "-Player 1 will be prompted to input the coordinate of a point on the 10x10 grid and then choose whether to face the ship down or to the right.\n");
      System.out.println(
          "-(Facing a ship a certain direction will ensure that the longer side points there, i.e the direction “right” for a ship (5x1) means that the ship will span 4 additional units right (for a total of 5 units) while staying in the same row to preserve its dimensional width of 1 unit)\n");
      System.out.println("-Outwit and stall the guesser with hard-to-guess configurations and arrangements\n");
      System.out.println("Player 2 (The guesser):\n");
      System.out.println(
          "-The games ends in your victory if you destroy all of the ships set by Player 1 through guessing their locations.\n");
      System.out.println(
          "-Input the x-coordinate and y-coordinate of your guess for the location of an enemy ship, whether a part of the enemy ship lies on that coordinate will be returned to you as feedback. \n");
      System.out.println(
          "-A 'hit' will be returned (with a 1 displayed on your grid) will show the presence of the enemy ship at that point, while a “miss” will be returned (with a 2 on the grid) if the enemy ship is not present.\n");
      System.out.println(
          "-A ship is destroyed when every one of the points it occupies on the grid are destroyed by 'hits' / the user’s guesses.\n");
      System.out.println(
          "-Strive to minimize the number of your guesses, the lower the number, the greater the performance!\n");
      System.out.println(m);
      //

      guessBoard = new Board(10, 10);
      opBoard.printBoard();
      System.out.println("PLAYER 1: Place your Ships\n");
      opBoard.manualSet();
      System.out.println(m);
      System.out.println(m);
      System.out.println(m);
      System.out.println("CONFIGURATION HAS BEEN SET");
      System.out.println("CONFIGURATION HAS BEEN SET");
      System.out.println("CONFIGURATION HAS BEEN SET\n");
      System.out.println("PLAYER 2: Please do not scroll up.");
      System.out.println(m);
      System.out.println("PLAYER 2: Guess");
    }

    // Start of Player guessing
    int numGuesses = 0;
    int shipsRemaining = opBoard.numShips;
    boolean gameEnd = false;
    // Guessing loop until the game Ends
    while (!gameEnd) {
      System.out.println("-----------------------------------------------------");

      int x;
      int y;
      // Error handling
      try {
        Scanner xCoordinate = new Scanner(System.in);
        System.out.print("X Coordinate of Guess: ");
        x = xCoordinate.nextInt();

        Scanner yCoordinate = new Scanner(System.in);
        System.out.print("Y Coordinate of Guess: ");
        y = yCoordinate.nextInt();
      } catch (Exception e) {
        System.out.println("\nInvalid Input: Make sure X and Y are positive integers");
        continue;
      }

      // More Error Handling
      try {
        // Checks if the guess is redundant
        if (guessBoard.getValue(x, y) == 0) {
          ++numGuesses;

          if (opBoard.getValue(x, y) == 1) {
            System.out.println("Hit!");
            guessBoard.setValue(x, y, 1);
            int rem = opBoard.numRemaining(guessBoard.getBoard());

            // If one less ship after the guess exists, the guess must have destroyed a ship
            if (rem == shipsRemaining - 1) {
              System.out.println("You destroyed a ship!");
              shipsRemaining = rem;
            }
            // If all ships are gone than terminate game
            if (shipsRemaining == 0) {
              System.out.println("----------------------------------");
              System.out.println("\nYou Won! In " + numGuesses + " guesses!");
              gameEnd = true;
              continue;
            }
          } else {
            // Handles misses
            System.out.println("\nMiss!\n");
            guessBoard.setValue(x, y, 2);
          }
          guessBoard.printBoard();
          System.out.println("Number of guesses: " + numGuesses);
          System.out.println("Ships remaining: " + shipsRemaining);
        } else {
          System.out.println("Already Guessed there");
        }
      } catch (Exception e) {
        System.out.println(
            "Invalid Input: Make sure your X and Y are within the grid and that all input is in the correct format.");
      }
    }

  }

  public static void drawGrid() {
    if(mode==0) { 
    Rectangle grid = new Rectangle(100, 100, 350, 350);
    for (int i = 100; i < 450; i += 50) {
      Text xlab = new Text("" + (i - 100) / 50, i, 100);
      Text ylab = new Text("" + (i - 100) / 50, 100, i);
      Line ver = new Line(i, 100, i, 450);
      Line hor = new Line(100, i, 450, i);
    }
    }
  }

}

// SECOND CLASS

class Board {

  int numRows;
  int numCols;
  final int numCoords = 4;
  int[][] ships;
  int numShips;
  // Stores coordinates of ships
  int[][] shipCoords;
  // The 2d Matrix which is basically the Game Board
  private int[][] gameBoard;

  // Constructor of Class (Allows for adjust of dimensions based on the mode)
  public Board(int m) {
    if (m == 1) {
      numRows = 10;
      numCols = 10;
      // X skewed (aka. length by width)
      ships = new int[][] { { 2, 1 }, { 3, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 } };
      numShips = 5;
    }
    if (m == 0 || m == -1) {
      numRows = 8;
      numCols = 8;
      // X skewed (aka. length by width)
      ships = new int[][] { { 2, 1 }, { 3, 1 }, { 5, 1 } };
      numShips = 3;
    }
    shipCoords = new int[numShips][numCoords];
    gameBoard = new int[numRows][numCols];
  }

  // Overload constructor for the guess object (The screen allowing the Player to
  // guess and display hits and misses)
  public Board(int a, int b) {
    gameBoard = new int[a][b];
    numRows = a;
    numCols = b;
  }

  // Default Constructor of Class
  public Board() {

  }

  // Prints the 2d game board matrix (with indexes to help with coordinate target)
  public void printBoard() {
   /* String output = "++ ";
    for (int i = 0; i < numCols; ++i) {
      output += i + " ";
    }
    output += "\n" + "----------------------------" + "\n";

    for (int r = 0; r < numRows; ++r) {
      output += r + "| ";
      for (int c = 0; c < numCols; ++c) {
        output += gameBoard[r][c] + " ";
      }
      output += "\n";
    }
    System.out.println(output); */

    //For Fast Mode draw
  if(Tester.mode == 0) {
     for(int i=0; i<7; ++i) {
       for(int j=0; j<7; ++j) {
         if(board[i][j] == 2) {
           Oval temp = new Oval(50*i + 100, 50*j +100, 20, 20);
           temp.fill()
         }
       }
     }
    
  }
  


    
  }

  // setter method (2d game board)
  public void setValue(int col, int row, int value) {
    gameBoard[row][col] = value;
  }

  // getter method (2d game board)
  public int getValue(int col, int row) {
    return gameBoard[row][col];
  }

  // returns the entire 2d game board matrix
  public int[][] getBoard() {
    return gameBoard;
  }

  // Random configuration of ships on grid during FAST MODE
  public void generate() {
    // Loops through each ship and individually randomizes them one at a time
    for (int move = 0; move < numShips; ++move) {
      int[] check = new int[numCoords];
      boolean valid = false;
      // While Loop continues until a valid ship position is found
      while (valid == false) {
        // Check is the array that contains the eventual positions of the randomized
        // ships
        check = random(ships[move][0], ships[move][1]);
        if (check[0] != -1) {

          for (int i = 0; i < numCoords; ++i) {
            shipCoords[move][i] = check[i];
          }
          // Fills the entire block of the game board so no overlap occurs
          fill(check[0], check[1], check[2], check[3]);
          valid = true;
        }
      }

    }
  }

  // Randomly determines the orientation of the ship (longest side downward, or
  // rightward)
  private int[] random(int x, int y) {
    int orientI = (numCols - x + 1) * (numRows - y + 1);
    int orientII = (numCols - y + 1) * (numRows - x + 1);
    int numPositions = orientI + orientII;

    // randomizes whether the orientation is to the right or down
    int rand = (int) (Math.random() * numPositions) + 1;
    if (rand > orientI) {
      // Orient down
      return randPos(y, x);
    } else {
      // Orient right
      return randPos(x, y);
    }
  }

  // Actually randomizes the coordinates creates the 'seed' x and y coordinates
  private int[] randPos(int len, int width) {
    int seedX = (int) (Math.random() * (numCols - len + 1));
    int seedY = (int) (Math.random() * (numRows - width + 1));

    if (verify(seedX, seedY, len, width)) {
      // This branch shows that the randomization is successful
      int[] array1 = { seedX, seedY, seedX + len, seedY + width };
      return array1;
    } else {
      // This branch returns that the randomization has failed/has a collision
      int[] array2 = { -1, -1, -1, -1 };
      return array2;
    }
  }

  // Verifies if a configuration of seedX, seedY with the proper dimensions does
  // not have any overlap with any other ships on the game board
  // (Keep in mind: The randomizaiton forces the ship to stay on the board, so
  // that type of error can be neglected)
  private boolean verify(int x, int y, int l, int w) {
    // Returns true if a ship in these coordinates and dimensions can land validly
    // in the grid
    for (int i = x; i < x + l; ++i) {
      for (int j = y; j < y + w; ++j) {
        if (gameBoard[j][i] == 1) {
          return false;
        }
      }
    }
    return true;
  }

  // Fills an entire rectangular section of the game board full of 1s
  private void fill(int x1, int y1, int x2, int y2) {
    for (int i = x1; i < x2; ++i) {
      for (int j = y1; j < y2; ++j) {
        gameBoard[j][i] = 1;
      }
    }
  }

  // User placement of ships (Player 1)
  public void manualSet() {
    int valid = 0;
    // Loops through ships and allows user to place ships one at a time
    for (int i = 0; i < numShips; ++i) {
      while (valid != 1) {
        // Try-Catch to eliminate errors in input
        try {
          // Placement input
          System.out.println("Placement of Ship: " + ships[i][0] + " x " + ships[i][1]);
          Scanner coordinateX = new Scanner(System.in);
          System.out.print("Place X-Coordinate of Ship at: ");
          int x = coordinateX.nextInt();

          Scanner coordinateY = new Scanner(System.in);
          System.out.print("Place Y-Coordinate of ship at: ");
          int y = coordinateY.nextInt();

          Scanner direc = new Scanner(System.in);
          System.out.print("0 for down, 1 for right: ");
          int direction = direc.nextInt();
          valid = secondary(x, y, direction, i);
        } catch (Exception e) {
          System.out.println(
              "\nInvalid Input: make sure X and Y are positive integers and within the grid and that all input is in the correct format.");
        }
        System.out.println("-----------------------------------------------------");
      }
      printBoard();
      valid = 0;
    }
  }

  // Follow up method to manualSet
  private int secondary(int x, int y, int direction, int row) {
    boolean result;

    int finalX;
    int finalY;

    // Changing finalX and finalY due to ship orientation
    if (direction == 1) {
      finalX = x + ships[row][0];
      finalY = y + ships[row][1];
    } else {
      if (direction == 0) {
        finalX = x + ships[row][1];
        finalY = y + ships[row][0];
      } else {
        System.out.println("Invalid Input");
        return 0;
      }
    }

    // finalX and finalY are actually never reached (More are upperBounds)
    if ((finalX > numCols) || (finalY > numRows)) {
      // Error handling in case placement is out of bounds
      System.out.println(
          "Out of Bounds: Make Sure that given the starting X and Y and the direction, the ship does not go off the screen");
      return 0;
    }

    // Use of the verification method
    result = verify(x, y, finalX - x, finalY - y);

    if (result) {
      // Inputting the ship coordinates into the matrix
      shipCoords[row][0] = x;
      shipCoords[row][1] = y;
      shipCoords[row][2] = finalX;
      shipCoords[row][3] = finalY;

      fill(x, y, finalX, finalY);
      return 1;
    }
    // If verification fails
    System.out.println("Collision with other ship: Remember ships can not overlap");
    return 0;
  }

  // Built in method showing number of remaining ships (not destroyed by player)
  public int numRemaining(int[][] guess) {
    int numDestroyed = 0;
    for (int i = 0; i < numShips; ++i) {

      boolean clean = true;
      // iterate to see if a ship given its coordinates completely matches with the
      // guesses of the player
      for (int j = shipCoords[i][0]; j < shipCoords[i][2]; ++j) {
        for (int k = shipCoords[i][1]; k < shipCoords[i][3]; ++k) {
          if (guess[k][j] == 0) {
            clean = false;
          }
        }
      }
      // if guess matrix matches with config matrix
      if (clean) {
        ++numDestroyed;
      }

    }
    return numShips - numDestroyed;
  }

}

class PushButton {
  private RoundRect shpButton; // Button Shape
  private boolean isOn; // Button state

  public PushButton() {
    // Create button shape and initialize
    shpButton = new RoundRect(100, 100, 75, 50, 20, 20);
    shpButton.setFillColor(200);

    // Starts off
    isOn = false;

    // Set button click event handler method
    shpButton.setMousePressedHandler(this::onPressed);
  }

  private void onPressed(Shape shp, double x, double y, int button) {
    // Toggle button state
    isOn = !isOn;

    // Set button fill color based on state
    if (isOn) {
      shpButton.setFillColor(0, 255, 0);
    } else {
      shpButton.setFillColor(200);
    }
  }
}

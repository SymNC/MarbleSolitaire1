package cs3500.marblesolitaire.model.hw02;

/**
 * implementation of Marble solitaire with a EnglishSolitaire board(plus shaped).
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final SlotState[][] board;
  private final int armThickness;
  private int score;

  /**
   * Creates a EnglishSolitaireModel with specified armThickness and starting empty cell position.
   *
   * @param armThickness the number of marbles in the top row(and bottom row, and left/right
   *                     columns)
   * @param sRow         empty slot starting Row
   * @param sCol         empty slot starting Column
   * @throws IllegalArgumentException if the arm thickness is not a positive * odd number, or the
   *                                  empty cell position is invalid.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("armThickness must be a positive odd number");
    }
    this.armThickness = armThickness;
    if (!inBounds(sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d,%d)", sRow, sCol));
    }
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.score = 0;
    this.makeBoard(sRow, sCol);


  }

  /**
   * Creates a EnglishSolitaireModel with specified armThickness, and the empty slot in the center.
   *
   * @param armThickness the number of marbles in the top row(and bottom row, and left/right
   *                     columns)
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) {
    this(armThickness, (armThickness * 3 - 2) / 2, (armThickness * 3 - 2) / 2);
  }

  /**
   * Creates a EnglishSolitaireModel with an armThickness of 3, and specified starting empty cell
   * position.
   *
   * @param sRow empty slot starting Row
   * @param sCol empty slot starting Column
   * @throws IllegalArgumentException empty cell position is invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }


  /**
   * initializes the game board with marbles,or invalid slotstates.
   */
  protected void makeBoard(int eRow, int eCol) {
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        if (inBounds(r, c)) {
          this.board[r][c] = SlotState.Marble;
          this.score++;
        } else {
          this.board[r][c] = SlotState.Invalid;
        }
      }
    }
    this.board[eRow][eCol] = SlotState.Empty;
    this.score--;
  }

  /**
   * returns true if the given cell corresponds to a valid cell on the the English solitaire model
   * board.
   *
   * @param row cell row
   * @param col cell column
   * @return true when the cell is within the bounds of the EnglishSolitaireModel board
   */
  protected boolean inBounds(int row, int col) {
    return ((row >= 0 && row < this.getBoardSize()) &&
            (col >= 0 && col < this.getBoardSize())) &&
            ((Math.abs(getBoardSize() / 2 - row) <= armThickness / 2)
                    || (Math.abs(getBoardSize() / 2 - col) <= armThickness / 2));
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move. and decrements the score.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (canMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[middleSlot(fromRow, toRow)][middleSlot(fromCol, toCol)] = SlotState.Empty;
      this.score--;

    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }

  /**
   * returns true if checks if you could move to the given cell location.
   */
  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (this.inBounds(fromRow, fromCol) && this.inBounds(toRow, toCol)
            && this.board[fromRow][fromCol] == SlotState.Marble
            && this.board[toRow][toCol] == SlotState.Empty
            && this.board[middleSlot(fromRow, toRow)][middleSlot(fromCol, toCol)]
            == SlotState.Marble
    ) {
      //cells are in the same row or col, and are correct distance apart
      return (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 0)
              || (Math.abs(fromRow - toRow) == 0 && Math.abs(fromCol - toCol) == 2);
    }

    return false;
  }

  //gets slot between 2 cells that are spaced 1 cell apart horizontally or vertically.
  private int middleSlot(int from, int to) {
    return (from + to) / 2;

  }


  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return this.score == 1 || !this.anyMoves();
  }

  /**
   * checks each slot to see if it is able to move in any direction.
   *
   * @return true if a more is possible
   */
  private boolean anyMoves() {
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {

        if (canMove(r, c, r + 2, c)
                || canMove(r, c, r + 2, c)
                || canMove(r, c, r, c - 2)
                || canMove(r, c, r, c + 2)) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness * 3 - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond the dimensions of the
   *                                  board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    try {
      return this.board[row][col];

    } catch (Exception e) {
      throw new IllegalArgumentException("out of board bounds" + e.getMessage());

    }
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return this.score;
  }
}

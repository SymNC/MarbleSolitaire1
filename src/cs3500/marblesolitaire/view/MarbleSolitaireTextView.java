package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * simple text view for MarbleSolitaire.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState game;

  /**
   * View for a MarbleSolitaireModelState.
   *
   * @param game MarbleSolitaire to view.
   * @throws IllegalArgumentException when given null game.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("MarbleSolitaireModelState null");
    }
    this.game = game;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int r = 0; r < game.getBoardSize(); r++) {
      for (int c = 0; c < game.getBoardSize(); c++) {

        if (c <= game.getBoardSize() / 2) {
          s.append(slotString(game.getSlotAt(r, c))).append(" ");
        }
        if (c == game.getBoardSize() - 1 && !game.getSlotAt(r, c)
                .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          s.append(slotString(game.getSlotAt(r, c)));
        }
        if (c > game.getBoardSize() / 2 && c <= game.getBoardSize() - 2) {

          if (!game.getSlotAt(r, c).equals(MarbleSolitaireModelState.SlotState.Invalid)
                  && game.getSlotAt(r, c + 1)
                  .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
            s.append(slotString(game.getSlotAt(r, c)));

          }
          if (!game.getSlotAt(r, c).equals(MarbleSolitaireModelState.SlotState.Invalid)
                  && !game.getSlotAt(r, c + 1)
                  .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
            s.append(slotString(game.getSlotAt(r, c))).append(" ");

          }
        }
      }

      if (r < game.getBoardSize() - 1) {
        s.append(System.lineSeparator());
      }
    }

    return s.toString();
  }

  private String slotString(MarbleSolitaireModelState.SlotState slot) {
    switch (slot) {
      case Empty:
        return "_";
      case Marble:
        return "O";
      case Invalid:
        return " ";
      default:
        return "";
    }
  }
}

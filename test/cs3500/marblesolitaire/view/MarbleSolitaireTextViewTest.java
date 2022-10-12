package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * test class for MarbleSolitaireTextView.
 */
public class MarbleSolitaireTextViewTest {

  MarbleSolitaireTextView mv3;

  StringBuilder board3x3;

  @Before
  public void setup() {
    mv3 = new MarbleSolitaireTextView(new EnglishSolitaireModel());

    board3x3 = new StringBuilder().append("    O O O").append(System.lineSeparator())
            .append("    O O O").append(System.lineSeparator())
            .append("O O O O O O O").append(System.lineSeparator())
            .append("O O O _ O O O").append(System.lineSeparator())
            .append("O O O O O O O").append(System.lineSeparator())
            .append("    O O O").append(System.lineSeparator())
            .append("    O O O");

  }

  @Test
  public void testToString() {
    assertEquals(board3x3.toString(), mv3.toString());
  }
}
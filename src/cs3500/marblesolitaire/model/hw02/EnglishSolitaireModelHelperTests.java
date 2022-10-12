package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for helper functions.
 */
public class EnglishSolitaireModelHelperTests {
  private EnglishSolitaireModel esm;

  @Before
  public void setup() {
    this.esm = new EnglishSolitaireModel(3);

  }

  @Test
  public void testCanMove() {
    assertFalse(esm.canMove(1, 3, 2, 3));
    assertFalse(esm.canMove(-1, 3, 2, 3));
    assertTrue(esm.canMove(3, 1, 3, 3));
    assertTrue(esm.canMove(1, 3, 3, 3));
    assertTrue(esm.canMove(5, 3, 3, 3));
    assertTrue(esm.canMove(3, 5, 3, 3));
  }

  @Test
  public void testInBounds() {
    assertTrue(esm.inBounds(3, 3));
    assertTrue(esm.inBounds(0, 3));
    assertTrue(esm.inBounds(3, 0));
    assertFalse(esm.inBounds(-3, 0));
    assertFalse(esm.inBounds(0, 0));
    assertFalse(esm.inBounds(0, 7));
  }

  @Test
  public void testMakeBoard() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm.getSlotAt(3, 3));
    esm.makeBoard(0, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm.getSlotAt(3, 3));

  }
}

package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * test class for EnglishSolitaireModel.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel esmDefault;
  private EnglishSolitaireModel esm5;
  private EnglishSolitaireModel esm1;


  @Before
  public void setup() {
    this.esmDefault = new EnglishSolitaireModel();
    this.esm5 = new EnglishSolitaireModel(5);
    this.esm1 = new EnglishSolitaireModel(1);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEven() {
    new EnglishSolitaireModel(2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNeg() {
    new EnglishSolitaireModel(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptySlot() {
    new EnglishSolitaireModel(-2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptySlot2() {
    new EnglishSolitaireModel(0, 0);
  }

  @Test
  public void testConstructorEmptySlot() {
    EnglishSolitaireModel esmEmpty = new EnglishSolitaireModel(5, 4, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esmEmpty.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esmEmpty.getSlotAt(6, 6));
  }

  @Test
  public void testValidMoves() {
    this.move(esmDefault, 3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esmDefault.getSlotAt(3, 3));
    this.move(esmDefault, 3, 4, 3, 2);

    this.move(esm5, 4, 6, 6, 6);
    this.move(esm5, 7, 6, 5, 6);

  }


  private void move(EnglishSolitaireModel s, int fromRow, int fromCol, int toRow, int toCol) {
    assertEquals(s.getSlotAt(fromRow, fromCol), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(s.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2),
            MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(s.getSlotAt(toRow, toCol), MarbleSolitaireModelState.SlotState.Empty);

    s.move(fromRow, fromCol, toRow, toCol);

    assertEquals(s.getSlotAt(fromRow, fromCol), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(s.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2),
            MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(s.getSlotAt(toRow, toCol), MarbleSolitaireModelState.SlotState.Marble);

  }

  @Test
  public void isGameOver() {
    assertFalse(esm5.isGameOver());
    assertTrue(esm1.isGameOver());
    assertFalse(esmDefault.isGameOver());
  }

  @Test
  public void getBoardSize() {
    assertEquals(1, esm1.getBoardSize());
    assertEquals(13, esm5.getBoardSize());
    assertEquals(7, esmDefault.getBoardSize());
  }

  @org.junit.Test
  public void getSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esmDefault.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esmDefault.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esmDefault.getSlotAt(0, 0));
  }

  @org.junit.Test
  public void getScore() {

    assertEquals(32, esmDefault.getScore());
    this.move(esmDefault, 3, 1, 3, 3);
    assertEquals(31, esmDefault.getScore());
    this.move(esmDefault, 3, 4, 3, 2);
    assertEquals(30, esmDefault.getScore());

  }
}
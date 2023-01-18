package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs5004.animator.model.IPoints;
import cs5004.animator.model.Point2D;
import org.junit.Test;

import java.util.Random;

/**
 * Test methods in class Point2D.
 * @author Yuhao Hua
 *
 */
public class TestPoint2D {

  /**
   * Test getters for construct.
   */
  @Test
  public void testGetters() {
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int x = Math.abs(rn.nextInt());
      int y = Math.abs(rn.nextInt());
      IPoints p = new Point2D(x, y);
      assertEquals(x, p.getX());
      assertEquals(y, p.getY());
    }
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int x = Math.abs(rn.nextInt());
      int y = Math.abs(rn.nextInt());
      IPoints p = new Point2D(x, y);
      String expection = "(" + x + ".0, " + y + ".0)";
      assertEquals(expection, p.toString());
    }
  }
  
  /**
   * Test isEqualTo method.
   */
  @Test
  public void testIsEqualTo() {
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int x = Math.abs(rn.nextInt());
      int y = Math.abs(rn.nextInt());
      IPoints p1 = new Point2D(x, y);
      IPoints p2 = new Point2D(x, y);
      IPoints p3 = new Point2D(x + 3, y);
      assertTrue(p1.isEqualTo(p2));
      assertFalse(p1.isEqualTo(p3));
    }
  }
}

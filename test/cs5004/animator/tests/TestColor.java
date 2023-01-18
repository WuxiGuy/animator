package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.Color;
import cs5004.animator.model.IColor;
import org.junit.Test;

import java.util.Random;


/**
 * Test for class Color.
 * @author Yuhao Hua
 *
 */
public class TestColor {

  /**
   * Test getters for constructor.
   */
  @Test
  public void testGetters() {
    Random rn = new Random();
    IColor col;
    for (int i = 0; i < 1000; i++) {
      int r = Math.abs(rn.nextInt()) % 255;
      int g = Math.abs(rn.nextInt()) % 255;
      int b = Math.abs(rn.nextInt()) % 255;
      col = new Color(r, g, b);
      assertEquals(r, col.getRed());
      assertEquals(g, col.getGreen());
      assertEquals(b, col.getBlue());
    }
  }
  
  /**
   * Test when input value is smaller than 0 or bigger than 255,
   * if an IllegalArgumentException will be thrown out.
   */
  @Test
  public void testConstructException() {
    Random rn = new Random();
    try {
      for (int i = 0; i < 1000; i++) {
        int r = - Math.abs(rn.nextInt()) % 255;
        int g = Math.abs(rn.nextInt()) % 255;
        int b = Math.abs(rn.nextInt()) % 255;
        new Color(r, g, b);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int r = Math.abs(rn.nextInt()) % 255;
        int g = - Math.abs(rn.nextInt()) % 255;
        int b = Math.abs(rn.nextInt()) % 255;
        new Color(r, g, b);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int r = Math.abs(rn.nextInt()) % 255;
        int g = Math.abs(rn.nextInt()) % 255;
        int b = Math.abs(rn.nextInt()) % 255 + 255;
        new Color(r, g, b);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int r = Math.abs(rn.nextInt()) % 255;
        int g = Math.abs(rn.nextInt()) % 255;
        int b = Math.abs(rn.nextInt()) % 255;
        new Color(r, g, b);
      }
    } catch (IllegalArgumentException e) {
      fail("An IllegalArgumentException should not be thrown out.");
    }
  }

  /**
   * Test toString for getters.
   */
  @Test
  public void testToString() {
    Random rn = new Random();
    IColor col;
    for (int i = 0; i < 1000; i++) {
      int r = Math.abs(rn.nextInt()) % 255;
      int g = Math.abs(rn.nextInt()) % 255;
      int b = Math.abs(rn.nextInt()) % 255;
      col = new Color(r, g, b);
      String expection = "(" + r + ".0, " + g + ".0, " + b + ".0)";
      assertEquals(expection, col.toString());
    }
  }
  
  /**
   * Test method isEqualTo().
   */
  @Test
  public void testIsEqualTo() {
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int r = Math.abs(rn.nextInt()) % 230;
      int g = Math.abs(rn.nextInt()) % 255;
      int b = Math.abs(rn.nextInt()) % 230;
      IColor col1 = new Color(r, g, b);
      IColor col2 = new Color(r, g, b);
      IColor col3 = new Color(r + 2, g, b + 3);
      assertTrue(col1.isEqualTo(col2));
      assertFalse(col1.isEqualTo(col3));
    }
  } 
}

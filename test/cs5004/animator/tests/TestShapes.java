package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Color;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeType;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Tests for IShape, both of Rectangle and Ellipse.
 * @author glenn_hyh
 *
 */
public class TestShapes {
  
  private Rectangle r;
  private Ellipse e;
  
  /**
   * Initialize two objects which are respectively for class
   * Rectangle and Ellipse.
   */
  @Before
  public void init() {
    r = new Rectangle("rec");
    e = new Ellipse("oval");
  }

  /**
   * Test changeLocation and getPosition methods.
   */
  @Test
  public void testCangeLocationAndGetPosition() {
    assertEquals(null, r.getPosition());
    assertEquals(null, e.getPosition());
    r.changeLocation(new Point2D(5, 6));
    e.changeLocation(new Point2D(50, 80));
    assertEquals(5, r.getPosition().getX());
    assertEquals(6, r.getPosition().getY());
    assertEquals(50, e.getPosition().getX());
    assertEquals(80, e.getPosition().getY());
    r.changeLocation(new Point2D(9, 10));
    e.changeLocation(new Point2D(7, 8));
    assertEquals(9, r.getPosition().getX());
    assertEquals(10, r.getPosition().getY());
    assertEquals(7, e.getPosition().getX());
    assertEquals(8, e.getPosition().getY());
  }

  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("rec", r.getName());
    assertEquals("oval", e.getName());
  }
  
  /**
   * Test getWidth and changeWidth methods.
   */
  @Test
  public void testGetWidthAndChangeWidth() {
    assertEquals(0.0, r.getWidth(), 0.01);
    assertEquals(0.0, e.getWidth(), 0.01);
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      double w = (double) Math.abs(rn.nextInt());
      r.changeWidth(w);
      e.changeWidth(w);
      assertEquals(w, r.getWidth(), 0.01);
      assertEquals(w, e.getWidth(), 0.01);
    }
  }
  
  /**
   * Test getHeight and changeHeight methods.
   */
  @Test
  public void testGetHeightAndChangeHeight() {
    assertEquals(0.0, r.getHeight(), 0.01);
    assertEquals(0.0, e.getHeight(), 0.01);
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      double w = (double) Math.abs(rn.nextInt());
      r.changeHeight(w);
      e.changeHeight(w);
      assertEquals(w, r.getHeight(), 0.01);
      assertEquals(w, e.getHeight(), 0.01);
    }
  }
  
  /**
   * Test getColor and changeColor methods.
   */
  @Test
  public void testGetColorAndChangeColor() {
    assertEquals(null, r.getColor());
    assertEquals(null, e.getColor());
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int re = Math.abs(rn.nextInt()) % 255;
      int g = Math.abs(rn.nextInt()) % 255;
      int b = Math.abs(rn.nextInt()) % 255;
      r.changeColor(new Color(re, g, b));
      e.changeColor(new Color(re, g, b));
      assertEquals(re, r.getColor().getRed());
      assertEquals(g, r.getColor().getGreen());
      assertEquals(b, r.getColor().getBlue());
      assertEquals(re, e.getColor().getRed());
      assertEquals(g, e.getColor().getGreen());
      assertEquals(b, e.getColor().getBlue());
    }
  }
  
  /**
   * Test methods getDisappearTime and changeDisappearTime.
   */
  @Test
  public void testGetDisappearTimeAndChangeDisappearTime() {
    assertEquals(0, r.getDisappearTime());
    assertEquals(0, e.getDisappearTime());
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int t = Math.abs(rn.nextInt()) % 255;
      r.changeDisappearTime(t);
      e.changeDisappearTime(t);
      assertEquals(t, r.getDisappearTime());
      assertEquals(t, e.getDisappearTime());
    }
  }
  
  /**
   * Test methods getAppearTime and changeAppearTime.
   */
  @Test
  public void testGetAppearTimeAndChangeAppearTime() {
    assertEquals(0, r.getAppearTime());
    assertEquals(0, e.getAppearTime());
    Random rn = new Random();
    for (int i = 0; i < 1000; i++) {
      int t = Math.abs(rn.nextInt()) % 255;
      r.changeAppearTime(t);
      e.changeAppearTime(t);
      assertEquals(t, r.getAppearTime());
      assertEquals(t, e.getAppearTime());
    }
  }
  
  /**
   * Test method getShapeType.
   */
  @Test
  public void testGetShapeType() {
    assertEquals(ShapeType.RECTANGLE, r.getShapeType());
    assertEquals(ShapeType.ELLIPSE, e.getShapeType());
  }
}

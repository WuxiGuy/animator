package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.IMotion;
import cs5004.animator.model.Motion;
import cs5004.animator.model.ShapeType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test methods in class Motion.
 * @author Yuhao Hua
 *
 */
public class TestMotion {
  
  private IMotion m1;
  private IMotion m2;
  
  /**
   * Initialize two motions.
   */
  @Before
  public void init() {
    m1 = new Motion("rec", ShapeType.RECTANGLE, 0, 5, 6, 50.0, 60.0, 0, 23, 60, 
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    m2 = new Motion("oval", ShapeType.ELLIPSE, 0, 5, 6, 50.0, 60.0, 0, 23, 6,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
  }

  /**
   * Test getters.
   */
  @Test
  public void testGetters() {
    assertEquals(0, m1.getStartTime());
    assertEquals(ShapeType.RECTANGLE, m1.getShapeType());
    assertEquals("rec", m1.getShapeName());
    assertEquals(5, m1.getInitialPoint().getX());
    assertEquals(6, m1.getInitialPoint().getY());
    assertEquals(50.0, m1.getInitialWidth(), 0.01);
    assertEquals(60.0, m1.getInitialHeight(), 0.01);
    assertEquals(0, m1.getInitialColor().getRed());
    assertEquals(23, m1.getInitialColor().getGreen());
    assertEquals(60, m1.getInitialColor().getBlue());
    assertEquals(100, m1.getEndTime());
    assertEquals(40, m1.getEndPoint().getX());
    assertEquals(10, m1.getEndPoint().getY());
    assertEquals(30.0, m1.getEndWidth(), 0.01);
    assertEquals(23.3, m1.getEndHeight(), 0.01);
    assertEquals(50, m1.getEndColor().getRed());
    assertEquals(20, m1.getEndColor().getGreen());
    assertEquals(44, m1.getEndColor().getBlue());
    
    assertEquals(0, m2.getStartTime());
    assertEquals(ShapeType.ELLIPSE, m2.getShapeType());
    assertEquals("oval", m2.getShapeName());
    assertEquals(5, m2.getInitialPoint().getX());
    assertEquals(6, m2.getInitialPoint().getY());
    assertEquals(50.0, m2.getInitialWidth(), 0.01);
    assertEquals(60.0, m2.getInitialHeight(), 0.01);
    assertEquals(0, m2.getInitialColor().getRed());
    assertEquals(23, m2.getInitialColor().getGreen());
    assertEquals(60, m2.getInitialColor().getBlue());
    assertEquals(100, m2.getEndTime());
    assertEquals(40, m2.getEndPoint().getX());
    assertEquals(10, m2.getEndPoint().getY());
    assertEquals(30.0, m2.getEndWidth(), 0.01);
    assertEquals(23.3, m2.getEndHeight(), 0.01);
    assertEquals(50, m2.getEndColor().getRed());
    assertEquals(20, m2.getEndColor().getGreen());
    assertEquals(44, m2.getEndColor().getBlue());
  }
}

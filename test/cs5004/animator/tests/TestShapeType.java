package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.ShapeType;
import org.junit.Test;

/**
 * Test enum ShapeType.
 * @author Yuhao Hua
 *
 */
public class TestShapeType {

  /**
   * Test method toString.
   */
  @Test
  public void testToString() {
    assertEquals("rectangle", ShapeType.RECTANGLE.toString());
    assertEquals("ellipse", ShapeType.ELLIPSE.toString());
  }

  /**
   * Test IllegalArgumentException for toString when wrong shape type.
   */
  @Test
  public void testToStringIllegalArgumentExceptionWrongType() {
    try {
      ShapeType.FORTEST.toString();
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      ShapeType.RECTANGLE.toString();
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
}

package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test class AnimationModel.
 * @author Yuhao Hua
 *
 */
public class TestAnimationModel {
  
  private IAnimationModel m;
  
  /**
   * Initialize model, shape.
   */
  @Before
  public void init() {
    m = new AnimationModel();
  }

  /**
   * Test construct using getters.
   */
  @Test
  public void testConstruct() {
    assertTrue(m.getMotionLibrary().isEmpty());
    assertTrue(m.getShapeLibrary().isEmpty());
    assertEquals(Integer.MAX_VALUE, m.getBeginTime());
    assertEquals(0, m.getFinishTime());
  }
  
  /**
   * Test methods addCanvas and getters for it.
   */
  @Test
  public void testAddCanvasAndGetters() {
    m.addCanvas(4, 7, 300, 400);
    assertEquals(4, m.getCanvasX());
    assertEquals(7, m.getCanvasY());
    assertEquals(300, m.getCanvasWidth());
    assertEquals(400, m.getCanvasHeight());
  }

  /**
   * Test method addShape.
   */
  @Test
  public void testAddShape() {
    m.addShape(ShapeType.RECTANGLE, "rec");
    assertEquals(1, m.getShapeLibrary().size());
    assertEquals(1, m.getMotionLibrary().keySet().size());
    assertTrue(m.getMotionLibrary().get("rec").isEmpty());
    assertEquals("rec", m.getShapeLibrary().get(0).getName());
    m.addShape(ShapeType.ELLIPSE, "oval");
    assertEquals(2, m.getShapeLibrary().size());
    assertEquals(2, m.getMotionLibrary().keySet().size());
    assertTrue(m.getMotionLibrary().get("oval").isEmpty());
    assertEquals("oval", m.getShapeLibrary().get(1).getName());
  }
  
  /**
   * Test exception IllegalArgumentException when shape is existing.
   */
  @Test
  public void testAddShapeIllegalArgumentExceptionShapeExist() {
    try {
      m.addShape(ShapeType.ELLIPSE, "rec");
      m.addShape(ShapeType.RECTANGLE, "rec");
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addShape(ShapeType.ELLIPSE, "oval");
      m.addShape(ShapeType.RECTANGLE, "oval");
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addShape(ShapeType.ELLIPSE, "we");
      m.addShape(ShapeType.RECTANGLE, "ew");
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
  
  /**
   * Test exception IllegalArgumentException when input wrong shape type.
   */
  @Test
  public void testAddShapeIllegalArgumentExceptionWrongShapeType() {
    try {
      m.addShape(ShapeType.FORTEST, "rec");
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addShape(ShapeType.ELLIPSE, "oval");
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
  
  /**
   * Test method addMotion.
   */
  @Test
  public void testAddMotion() {
    m.addShape(ShapeType.ELLIPSE, "oval");
    m.addShape(ShapeType.RECTANGLE, "rec");
    m.addMotion("rec", ShapeType.RECTANGLE, 0, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    m.addMotion("oval", ShapeType.ELLIPSE, 0, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    assertEquals(1, m.getMotionLibrary().get("oval").size());
    assertEquals(1, m.getMotionLibrary().get("rec").size());
    assertEquals(50.0, m.getMotionLibrary().get("oval").get(0).getInitialWidth(), 0.01);
    assertEquals(44, m.getMotionLibrary().get("oval").get(0).getEndColor().getBlue());
    assertEquals(50.0, m.getMotionLibrary().get("rec").get(0).getInitialWidth(), 0.01);
    assertEquals(44, m.getMotionLibrary().get("rec").get(0).getEndColor().getBlue());
  }
  
  /**
   * Test IllegalArgumentException for addMotion when start time is later
   * than end time.
   */
  @Test
  public void testAddMotionIllegalArgumentExceptionWrongDuration() {
    try {
      m.addShape(ShapeType.ELLIPSE, "oval");
      m.addMotion("oval", ShapeType.ELLIPSE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
          49, 40, 10, 30.0, 23.3, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addShape(ShapeType.RECTANGLE, "rec");
      m.addMotion("rec", ShapeType.RECTANGLE, 50, 5, 6, 50.0, 60.0, 0, 23, 80,
          49, 40, 10, 30.0, 23.3, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addMotion("rec", ShapeType.RECTANGLE, 0, 5, 6, 50.0, 60.0, 0, 23, 60,
          100, 40, 10, 30.0, 23.3, 50, 20, 44);
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
  
  /**
   * Test IllegalArgumentException for addMotion when shape name doesn't
   * match the shape type.
   */
  @Test
  public void testAddMotionIllegalArgumentExceptionWrongShapeType() {
    try {
      m.addShape(ShapeType.ELLIPSE, "oval");
      m.addMotion("oval", ShapeType.RECTANGLE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
          80, 40, 10, 30.0, 23.3, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addShape(ShapeType.RECTANGLE, "rec");
      m.addMotion("rec", ShapeType.ELLIPSE, 50, 5, 6, 50.0, 60.0, 0, 23, 80,
          100, 40, 10, 30.0, 23.3, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.addMotion("rec", ShapeType.RECTANGLE, 0, 5, 6, 50.0, 60.0, 0, 23, 60,
          100, 40, 10, 30.0, 23.3, 50, 20, 44);
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
  
  /**
   * Test method getBeginTime.
   */
  @Test
  public void testGetBeginTime() {
    m.addShape(ShapeType.ELLIPSE, "oval");
    m.addShape(ShapeType.RECTANGLE, "rec");
    m.addMotion("rec", ShapeType.RECTANGLE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    assertEquals(50, m.getBeginTime());
    m.addMotion("oval", ShapeType.ELLIPSE, 40, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    assertEquals(40, m.getBeginTime());
  }
  
  /**
   * Test method getFinishTime.
   */
  @Test
  public void testGetFinishTime() {
    m.addShape(ShapeType.ELLIPSE, "oval");
    m.addShape(ShapeType.RECTANGLE, "rec");
    m.addMotion("rec", ShapeType.RECTANGLE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    assertEquals(100, m.getFinishTime());
    m.addMotion("oval", ShapeType.ELLIPSE, 40, 5, 6, 50.0, 60.0, 0, 23, 60,
        150, 40, 10, 30.0, 23.3, 50, 20, 44);
    assertEquals(150, m.getFinishTime());
  }
  
  /**
   * Test method getShapesAtMoment.
   */
  @Test
  public void testGetShapesAtMoment() {
    m.addShape(ShapeType.RECTANGLE, "rec");
    m.addMotion("rec", ShapeType.RECTANGLE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
        100, 40, 10, 30.0, 23.3, 50, 20, 44);
    ArrayList<IShape> s = (ArrayList<IShape>) m.getShapesAtMoment(75);
    assertEquals(8, s.get(0).getPosition().getY());
    assertEquals(52, s.get(0).getColor().getBlue());
    assertEquals(40.0,s.get(0).getWidth(), 0.01);
  }
  
  /**
   * Test IllegalArgumentException for getShapesAtMoment when input time
   * is out of range.
   */
  @Test
  public void testGetShapesAtMomentIllegalArgumentExceptionWrongTime() {
    try {
      m.addShape(ShapeType.ELLIPSE, "oval");
      m.addMotion("oval", ShapeType.ELLIPSE, 50, 5, 6, 50.0, 60.0, 0, 23, 60,
          80, 40, 10, 30.0, 23.3, 50, 20, 44);
      m.getShapesAtMoment(20);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.getShapesAtMoment(200);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      m.getShapesAtMoment(60);
    } catch (IllegalArgumentException e) {
      fail("No IllegalArgumentException should be thrown out.");
    }
  }
}

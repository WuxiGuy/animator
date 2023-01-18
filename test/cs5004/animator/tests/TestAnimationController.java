package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ShapeType;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Test class AnimationController and builder.
 * @author Yuhao Hua
 *
 */
public class TestAnimationController {

  /**
   * Test construct by input command line in String[].
   */
  @Test
  public void testBuilderConstruct() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    try {
      AnimationReader.parseFile(new BufferedReader(new FileReader("toh-3.txt")), b);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(145, m.getCanvasX());
    assertEquals(410, m.getCanvasWidth());
    assertEquals("disk1", m.getShapeLibrary().get(0).getName());
    assertEquals(302, m.getShapeLibrary().get(0).getDisappearTime());
    assertEquals(302, m.getFinishTime());
    assertEquals(90, m.getMotionLibrary().get("disk1").get(0).getEndColor().getBlue());
    assertEquals(90, m.getMotionLibrary().get("disk1").get(1).getEndColor().getBlue());
  }
  
  /**
   * Test builder setBounds.
   */
  @Test
  public void testBuilderSetBounds() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    for (int i = 0; i < 1000; i++) {
      int x = i;
      int y = i;
      int w = x + i + 1;
      int h = y + i + 1;
      b.setBounds(x, y, w, h);
      assertEquals(x, m.getCanvasX());
      assertEquals(y, m.getCanvasY());
      assertEquals(w, m.getCanvasWidth());
      assertEquals(h, m.getCanvasHeight());
    }
  }
  
  /**
   * Test IllegalArgumentException for builder setBounds 
   * when width and height is not right.
   */
  @Test
  public void testBuilderSetBoundsIllegalArgumentExceptionWrongSize() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    try {
      for (int i = 0; i < 1000; i++) {
        int x = i;
        int y = i;
        int w = - (x + i + 1);
        int h = y + i + 1;
        b.setBounds(x, y, w, h);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int x = i;
        int y = i;
        int w = x + i + 1;
        int h = - (y + i + 1);
        b.setBounds(x, y, w, h);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
  }
  
  /**
   * Test IllegalArgumentException for builder setBounds 
   * when position-x and position-y is not right.
   */
  @Test
  public void testBuilderSetBoundsIllegalArgumentExceptionWrongPosition() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    try {
      for (int i = 0; i < 1000; i++) {
        int x = i + 20;
        int y = i + 20;
        int w = i + 1;
        int h = i + 1;
        b.setBounds(x, y, w, h);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int x = i - 20;
        int y = i + 10;
        int w = i + 1;
        int h = i + 1;
        b.setBounds(x, y, w, h);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
  }
  
  /**
   * Test builder method declareShape.
   */
  @Test
  public void testDeclareShape() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    b.declareShape("rec", "rectangle");
    b.declareShape("oval", "ellipse");
    assertEquals(ShapeType.RECTANGLE, m.getShapeLibrary().get(0).getShapeType());
    assertEquals(ShapeType.ELLIPSE, m.getShapeLibrary().get(1).getShapeType());
    assertEquals("rec", m.getShapeLibrary().get(0).getName());
    assertEquals("oval", m.getShapeLibrary().get(1).getName());
  }
  
  /**
   * Test IllegalArgumentException for builder declareShape
   * when shape type is wrong.
   */
  @Test
  public void testDeclareShapeIllegalArgumentExceptionWrongShapeType() {
    try {
      AnimationModel m = new AnimationModel();
      AnimationBuilderImpl b = new AnimationBuilderImpl(m);
      b.declareShape("rec", "circle");
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      AnimationModel m = new AnimationModel();
      AnimationBuilderImpl b = new AnimationBuilderImpl(m);
      b.declareShape("rec", "triangle");
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
  }
  
  /**
   * Test builder addMoton.
   */
  @Test
  public void testAddMotion() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    b.declareShape("rec", "rectangle");
    b.declareShape("oval", "ellipse");
    b.addMotion("rec", 5, 5, 6, 50, 60, 0, 23, 60, 100, 40, 10, 30, 23, 50, 20, 44);
    b.addMotion("oval", 10, 5, 6, 50, 60, 0, 23, 60, 120, 40, 10, 30, 23, 50, 20, 44);
    assertEquals(5, m.getBeginTime());
    assertEquals(120, m.getFinishTime());
    assertEquals(50.0, m.getShapeLibrary().get(0).getWidth(), 0.01);
    assertEquals(50.0, m.getShapeLibrary().get(1).getWidth(), 0.01);
  }
  
  /**
   * Test IllegalArgumentException for builder addMotion
   * when duration is not right.
   */
  @Test
  public void testBuilderAddMotionIllegalArgumentExceptionWrongTime() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    b.declareShape("rec", "rectangle");
    b.declareShape("oval", "ellipse");
    try {
      for (int i = 0; i < 1000; i++) {
        int t1 = i + 20;
        int t2 = i;
        b.addMotion("rec", t1, 5, 6, 50, 60, 0, 23, 60, t2, 40, 10, 30, 23, 50, 20, 44);
        fail("An IllegalArgumentException should be thrown out.");
      }
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      for (int i = 0; i < 1000; i++) {
        int t1 = i;
        int t2 = i + 20;
        b.addMotion("rec", t1, 5, 6, 50, 60, 0, 23, 60, t2, 40, 10, 30, 23, 50, 20, 44);
      }
    } catch (IllegalArgumentException e) {
      fail("An IllegalArgumentException should not be thrown out.");
    }
  }
  
  /**
   * Test IllegalArgumentException for builder addMotion
   * when size is not right.
   */
  @Test
  public void testBuilderAddMotionIllegalArgumentExceptionWrongSize() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    b.declareShape("rec", "rectangle");
    b.declareShape("oval", "ellipse");
    try {
      b.addMotion("rec", 0, 5, 6, 50, -1, 0, 23, 60, 100, 40, 10, 30, 23, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      b.addMotion("rec", 20, 5, 6, 50, 60, 0, 23, 60, 80, 40, 10, 30, 23, 50, 20, 44);
    } catch (IllegalArgumentException e) {
      fail("An IllegalArgumentException should not be thrown out.");
    }
  }
  
  /**
   * Test IllegalArgumentException for builder addMotion
   * when color is not right.
   */
  @Test
  public void testBuilderAddMotionIllegalArgumentExceptionWrongColor() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    b.declareShape("rec", "rectangle");
    b.declareShape("oval", "ellipse");
    try {
      b.addMotion("rec", 0, 5, 6, 50, 1, 0, 23, 300, 100, 40, 10, 30, 23, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      b.addMotion("rec", 0, 5, 6, 50, 1, 0, 23, -25, 100, 40, 10, 30, 23, 50, 20, 44);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
    try {
      b.addMotion("rec", 20, 5, 6, 50, 60, 0, 23, 60, 80, 40, 10, 30, 23, 50, 20, 44);
    } catch (IllegalArgumentException e) {
      fail("An IllegalArgumentException should not be thrown out.");
    }
  }
}

package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextAnimationView;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Test class TextAnimationView.
 * @author Yuhao Hua
 *
 */
public class TestTextAnimationView {

  private TextAnimationView v;
  
  /**
   * Initialize some data for testing.
   */
  @Before
  public void init() {
    AnimationModel m = new AnimationModel();
    AnimationBuilderImpl b = new AnimationBuilderImpl(m);
    try {
      AnimationReader.parseFile(new BufferedReader(new FileReader("toh-3.txt")), b);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.v = new TextAnimationView(m, 10);
  }

  /**
   * Test if output string is created in right way.
   */
  @Test
  public void testToString() {
    String[] lines = this.v.toString().split("\n");
    String firstMsg = String.join("\n", Arrays.copyOfRange(lines, 0, 5));
    String expected = "Name: disk1\n"
        + "Type: rectangle\n"
        + "Min corner: (190.0, 180.0), Width: 20.0, Height: 30.0, Color: (0.0, 49.0, 90.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=302";
    assertEquals(expected, firstMsg);
    expected = "Shape disk1 scales from Width: 20.0, Height: 30.0 to Width: 20.0, "
        + "Height: 30.0 from t=25700ms to t=30200ms\n"
        + "Shape disk1 changes color from (0.0, 255.0, 0.0) to (0.0, 255.0, 0.0) "
        + "from t=25700ms to t=30200ms";
    firstMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 2, lines.length));
    assertEquals(expected, firstMsg);
  }
  
  /**
   * Test IllegalArgumentException for calling method listenAction.
   */
  @Test
  public void testListenActionIllegalArgumentException() {
    try {
      this.v.listenAction(null);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
  }
  
  /**
   * Test IllegalArgumentException for calling method updateShapeList.
   */
  @Test
  public void testUpdateShapeListIllegalArgumentException() {
    try {
      this.v.updateShapeList(null);
      fail("An IllegalArgumentException should be thrown out.");
    } catch (IllegalArgumentException e) {
      //Nothing should be caught.
    }
  }
}

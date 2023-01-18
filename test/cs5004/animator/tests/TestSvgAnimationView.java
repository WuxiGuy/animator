package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SvgAnimationView;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Test class SVGAnimationView.
 * @author Yuhao Hua
 *
 */
public class TestSvgAnimationView {

  private SvgAnimationView v;
  
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
    this.v = new SvgAnimationView(m, 10);
  }
  
  /**
   * Test if output string is created in right way.
   */
  @Test
  public void testToString() {
    String[] lines = this.v.toString().split("\n");
    String firstMsg = String.join("\n", Arrays.copyOfRange(lines, 0, 5));
    String expected = "<svg width=\"410\" height=\"220\" viewBox=\"145 50 410 220\" "
        + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20.0\" height=\"30.0\" "
        + "fill=\"rgb(0.0, 49.0, 90.0)\" visibility=\"hidden\" >\n"
        + "<animate attributeType=\"xml\" begin=\"100ms\" dur=\"1.0ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"1000ms\" "
        + "attributeName=\"x\" from=\"190\" to=\"190\" fill=\"freeze\" />";
    assertEquals(expected, firstMsg);
    expected = "<animate attributeType=\"xml\" begin=\"15300ms\" dur=\"1000ms\" "
        + "attributeName=\"y\" from=\"50\" to=\"240\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15300ms\" dur=\"800ms\" "
        + "attributeName=\"fill\" from=\"rgb(11.0, 45.0, 175.0)\" "
        + "to=\"rgb(0.0, 255.0, 0.0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "</svg>";
    firstMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 5, lines.length));
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

package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IMotion;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SVGAnimationView class that plays animation in svg mode.
 * @author Yuhao Hua
 *
 */
public class SvgAnimationView implements IAnimationView {

  private IAnimationModel model;
  private StringBuilder sb;
  private int speed;
  
  /**
   * Build constructor for SVGAnimation class.
   * @param model AnimationModel contains information of shape and motion
   * @param speed play speed of animation
   */
  public SvgAnimationView(IAnimationModel model, int speed) {
    this.model = model;
    this.sb = new StringBuilder();
    this.speed = speed;
    buildSvgContent();
  }
  
  /**
   * Create animation in form of svg.
   */
  private void buildSvgContent() {
    String str = "<svg width=\"" + model.getCanvasWidth() + "\" height=\""
        + model.getCanvasHeight() + "\" viewBox=\"" + model.getCanvasX() 
        + " " + model.getCanvasY() + " "
        + model.getCanvasWidth() + " " + model.getCanvasHeight()
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    this.sb.append(str);
    
    for (IShape shape:this.model.getShapeLibrary()) {
      if (this.model.getShapeLibrary().isEmpty()) {
        throw new IllegalArgumentException("No motion for this shape.");
      }
      
      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        this.sb.append(addRectangle(shape));
        for (IMotion motion:this.model.getMotionLibrary().get(shape.getName())) {
          addMotion(motion, shape);
        }
        this.sb.append("</rect>\n\n");
      }
      if (shape.getShapeType() == ShapeType.ELLIPSE) {
        this.sb.append(addEllipse(shape));
        for (IMotion motion:this.model.getMotionLibrary().get(shape.getName())) {
          addMotion(motion, shape);
        }
        this.sb.append("</ellipse>\n\n");
      }
    }
    this.sb.append("</svg>");
  }
  
  /**
   * Add rectangle to animation.
   * @param shape a rectangle
   * @return String of animation in svg
   */
  private String addRectangle(IShape shape) {
    return "<rect id=\"" + shape.getName() + "\" x=\"" 
            + (int) shape.getPosition().getX() + "\" y=\""
            + (int) shape.getPosition().getY() 
            + "\" width=\"" + shape.getWidth() + "\" height=\""
            + shape.getHeight() + "\" fill=\"rgb" 
            + shape.getColor().toString() + "\" visibility="
            + "\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"" 
            + shape.getAppearTime() * 1000 / speed + "ms\" "
            + "dur=\"1.0ms\" attributeName=\"visibility\" "
            + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n";
  }
  
  /**
   * Add ellipse to animation.
   * @param shape a ellipse
   * @return String of animation in form of svg
   */
  private String addEllipse(IShape shape) {
    return "<ellipse id=\"" + shape.getName() + "\" cx=\"" + (int) shape.getPosition().getX()
            + "\" cy=\"" + (int) shape.getPosition().getY() 
            + "\" rx=\"" + shape.getWidth() / 2 + "\" ry=\""
            + shape.getHeight() / 2 + "\" fill=\"rgb" 
            + shape.getColor().toString() + "\" visibility="
            + "\"hidden\" >\n"
            + "<animate attributeType=\"xml\" begin=\"" 
            + shape.getAppearTime() * 1000 / speed + "ms\" "
            + "dur=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\" "
            + "to=\"visible\" fill=\"freeze\" />\n";
  }

  /**
   * Add motion of shape to animation.
   * @param motion needs to be added
   * @param shape owner of the motion
   * @return String of motion in form of svg
   */
  private void addMotion(IMotion motion, IShape shape) {
    int duration = (motion.getEndTime() - motion.getStartTime()) * 1000 / speed;
    if (!motion.getInitialPoint().isEqualTo(motion.getEndPoint())) {
      this.addPointChangeMotion(motion, shape, duration);
    }
    if (!(motion.getInitialWidth() == motion.getEndWidth()
        || motion.getInitialHeight() == motion.getEndHeight())) {
      this.addSizeChangeMotion(motion, shape, duration);
    }
    if (!motion.getInitialColor().isEqualTo(motion.getEndColor())) {
      this.addColorChangeMotion(motion, shape, duration);
    }
  }
  
  /**
   * Build up action information about change position.
   * @param motion needs to be added
   * @param shape owner of the motion
   * @param duration length of time of the motion
   */
  private void addPointChangeMotion(IMotion motion, IShape shape, int duration) {
    if (shape.getShapeType() == ShapeType.RECTANGLE) {
      this.sb.append("<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\" "
          + "dur=\"" + duration + "ms\" attributeName=\"x\" from=\""
          + (int) motion.getInitialPoint().getX() + "\" to=\"" + (int) motion.getEndPoint().getX() 
          + "\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"" + motion.getEndTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"y\" from=\""
          + (int) motion.getInitialPoint().getY() + "\" to=\"" + (int) motion.getEndPoint().getY()
          + "\" fill=\"freeze\" />\n");
    } else if (shape.getShapeType() == ShapeType.ELLIPSE) {
      this.sb.append("<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\" "
          + "dur=\"" + duration + "ms\" attributeName=\"cx\" from=\""
          + (int) motion.getInitialPoint().getX() + "\" to=\"" + (int) motion.getEndPoint().getX()
          + "\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"" + motion.getEndTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"cy\" from=\""
          + (int) motion.getInitialPoint().getY() + "\" to=\"" + (int) motion.getEndPoint().getY()
          + "\" fill=\"freeze\" />\n");
    }
  }
  
  /**
   * Build up action information about change size.
   * @param motion needs to be added
   * @param shape owner of the motion
   * @param duration length of time of the motion
   */
  private void addSizeChangeMotion(IMotion motion, IShape shape, int duration) {
    if (shape.getShapeType() == ShapeType.RECTANGLE) {
      this.sb.append("<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"width\" from=\""
          + motion.getInitialWidth() + "\" to=\"" 
          + motion.getEndWidth() + "\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"height\" from=\""
          + motion.getInitialHeight() + "\" to=\"" 
          + motion.getEndHeight() + "\" fill=\"freeze\" />\n");
    } else if (shape.getShapeType() == ShapeType.ELLIPSE) {
      this.sb.append("<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"rx\" from=\""
          + motion.getInitialWidth() / 2 + "\" to=\"" 
          + motion.getEndWidth() / 2 + "\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"" 
          + motion.getStartTime() * 1000 / speed + "ms\""
          + " dur=\"" + duration + "ms\" attributeName=\"ry\" from=\""
          + motion.getInitialHeight() / 2 + "\" to=\"" 
          + motion.getEndHeight() / 2 + "\" fill=\"freeze\" />\n");
    }
  }
  
  /**
   * Build up action information about change color.
   * @param motion needs to be added
   * @param shape owner of the motion
   * @param duration length of time of the motion
   */
  private void addColorChangeMotion(IMotion motion, IShape shape, int duration) {
    this.sb.append("<animate attributeType=\"xml\" begin=\"" 
        + motion.getStartTime() * 1000 / speed + "ms\""
        + " dur=\"" + duration + "ms\" attributeName=\"fill\" from=\"rgb"
        + motion.getInitialColor().toString() + "\" to=\"rgb" + motion.getEndColor().toString()
        + "\" fill=\"freeze\" />\n");
  }
  
  @Override
  public void listenAction(AnimationController aController) {
    throw new IllegalArgumentException("Can not use this method for this class.");
  }

  @Override
  public void playAnimation() {
    System.out.print(this.sb.toString());
  }
  
  @Override
  public String toString() {
    return this.sb.toString();
  }

  @Override
  public void createFile(String fileName) {
    try {
      FileWriter outputFile = new FileWriter(fileName);
      outputFile.write(this.sb.toString());
      outputFile.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void updateShapeList(ArrayList<IShape> newList) {
    throw new IllegalArgumentException("Can not use this method for this class.");
  }
}

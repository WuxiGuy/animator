package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IMotion;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * TextAnimationView class that plays animation in text mode.
 * @author Yuhao Hua
 *
 */
public class TextAnimationView implements IAnimationView {
  
  private IAnimationModel model;
  private StringBuilder sb;
  private int speed;
  
  /**
   * Build constructor for TextAnimation class.
   * @param model AnimationModel contains information of shape and motion
   * @param speed play speed of animation
   */
  public TextAnimationView(IAnimationModel model, int speed) {
    this.model = model;
    this.sb = new StringBuilder();
    this.speed = speed;
    try {
      buildShapeText();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    buildMotionText();
  }
  
  /**
   * Create animation in form of text.
   * @throws ClassNotFoundException when no this type of shape
   */
  private void buildShapeText() throws ClassNotFoundException {
    String output = "";
    for (IShape shape:this.model.getShapeLibrary()) {
      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        output += "Name: " + shape.getName() + "\n"
            + "Type: rectangle\n"
            + "Min corner: " + shape.getPosition().toString() + ", Width: "
            + String.format("%.1f", shape.getWidth()) + ", Height: "
            + String.format("%.1f", shape.getHeight())
            + ", Color: " + shape.getColor().toString() + "\n"
            + "Appears at t=" + String.valueOf(shape.getAppearTime() * 10 / this.speed) + "\n"
            + "Disappears at t=" 
            + String.valueOf(shape.getDisappearTime() * 10 / this.speed) + "\n\n";
      } else if (shape.getShapeType() == ShapeType.ELLIPSE) {
        output += "Name: " + shape.getName() + "\n"
            + "Type: ellipse\n"
            + "Min corner: " + shape.getPosition().toString() + ", X radius: "
            + String.format("%.1f", shape.getWidth()) + ", Y radius: "
            + String.format("%.1f", shape.getHeight())
            + ", Color: " + shape.getColor().toString() + "\n"
            + "Appears at t=" + String.valueOf(shape.getAppearTime() * 1000 / this.speed) + "ms\n"
            + "Disappears at t=" 
            + String.valueOf(shape.getDisappearTime() * 1000 / this.speed) + "ms\n\n";
      } else {
        throw new ClassNotFoundException("No such kind of shape.");
      }
    }
    this.sb.append(output);
  }
  
  /**
   * Add motion information to animation in form of text.
   */
  private void buildMotionText() {
    String output = "";
    ArrayList<IMotion> motionListBeforeSorted = new ArrayList<>();
    for (IShape shape:this.model.getShapeLibrary()) {
      for (IMotion motion:(this.model.getMotionLibrary().get(shape.getName()))) {
        motionListBeforeSorted.add(motion);
      }
    }
    Comparator<IMotion> sortMotionTool = Comparator.comparingInt(IMotion::getStartTime);
    ArrayList<IMotion> motionList = motionListBeforeSorted.stream().map(element -> element)
        .sorted(sortMotionTool).collect(Collectors.toCollection(ArrayList::new));
    
    
    for (IMotion motion:motionList) {
      String timePeriod = " from t=" 
          + String.valueOf(motion.getStartTime() * 1000 / this.speed) + "ms to t="
          + String.valueOf(motion.getEndTime() * 1000 / this.speed) + "ms\n";
      output += "Shape " + motion.getShapeName() + " moves from "
          + motion.getInitialPoint().toString() + " to " + motion.getEndPoint().toString()
          + timePeriod;
      output += "Shape " + motion.getShapeName();
      if (motion.getShapeType() == ShapeType.RECTANGLE) {
        output += " scales from Width: "
            + String.format("%.1f", motion.getInitialWidth()) + ", Height: "
            + String.format("%.1f", motion.getInitialHeight())
            + " to Width: " + String.format("%.1f", motion.getEndWidth()) + ", Height: "
            + String.format("%.1f", motion.getEndHeight()) + timePeriod;
      } else if (motion.getShapeType() == ShapeType.ELLIPSE) {
        output += " scales from X radius: "
            + String.format("%.1f", motion.getInitialWidth())
            + ", Y radius: " + String.format("%.1f", motion.getInitialHeight())
            + " to X radius: " + String.format("%.1f", motion.getEndWidth())
            + ", Y radius: " + String.format("%.1f", motion.getEndHeight()) + timePeriod;
      }
      output += "Shape " + motion.getShapeName() + " changes color from "
          + motion.getInitialColor().toString() + " to "
          + motion.getEndColor().toString() + timePeriod;
    }
    this.sb.append(output);
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

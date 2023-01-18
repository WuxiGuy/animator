package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Model class deal with the command received from controller.
 * It helps create shapes and add motions to them.
 * Stores the information of canvas.
 * @author Yuhao Hua
 *
 */
public class AnimationModel implements IAnimationModel {
  
  private HashMap<String, ArrayList<IMotion>> motionLibrary;
  private ArrayList<IShape> shapeLibrary;
  private int canvasX;
  private int canvasY;
  private int canvasWidth;
  private int canvasHeight;
  private int finishTime;
  private int beginTime;
  
  /**
   * Constructor of class AnimationModel.
   */
  public AnimationModel() {
    this.motionLibrary = new HashMap<String, ArrayList<IMotion>>();
    this.shapeLibrary = new ArrayList<IShape>();
    this.finishTime = 0;
    this.beginTime = Integer.MAX_VALUE;
  }

  @Override
  public void addShape(ShapeType shapeType, String shapeName) 
      throws IllegalArgumentException {
    for (IShape shape:shapeLibrary) {
      if (shape.getName() == shapeName) {
        throw new IllegalArgumentException("This shape is already existing.");
      }
    }
    switch (shapeType) {
      case RECTANGLE:
        this.shapeLibrary.add(new Rectangle(shapeName));
        this.motionLibrary.put(shapeName, new ArrayList<IMotion>());
        break;
      case ELLIPSE:
        this.shapeLibrary.add(new Ellipse(shapeName));
        this.motionLibrary.put(shapeName, new ArrayList<IMotion>());
        break;
      default:
        throw new IllegalArgumentException("Can't create this type of shape.");
    }
  }

  @Override
  public void addMotion(String nameOfShape, ShapeType shapeType, int startTime,
      int initialX, int initialY, double initialWidth, double initialHeight,
      int initialR, int initialG, int initialB,
      int endTime, int endX, int endY, double endWidth, 
      double endHeight, int endR, int endG, int endB) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time should not be later than end time.");
    }
    
    for (IShape sh : this.getShapeLibrary()) {
      if (sh.getName() == nameOfShape) {
        if (sh.getShapeType() != shapeType) {
          throw new IllegalArgumentException("Wrong shape type for this shape");
        }
      }
    }
    
    for (String shape:this.motionLibrary.keySet()) {
      if (shape.equals(nameOfShape)) {
        if (this.motionLibrary.get(shape).size() != 0) {
          (this.motionLibrary.get(shape)).add(
              new Motion(nameOfShape, shapeType, startTime, initialX, 
                  initialY, initialWidth, initialHeight,
                  initialR, initialG, initialB, endTime, endX, endY, endWidth, endHeight,
                  endR, endG, endB));
          for (IShape s:this.shapeLibrary) {
            if (s.getName().equals(nameOfShape)) {
              s.changeDisappearTime(endTime);
            }
          }
          
        } else if (this.motionLibrary.get(nameOfShape).size() == 0) {
          (this.motionLibrary.get(nameOfShape)).add(
              new Motion(nameOfShape, shapeType, startTime, initialX, 
                  initialY, initialWidth, initialHeight,
                  initialR, initialG, initialB, endTime, endX, endY, endWidth, endHeight,
                  endR, endG, endB));
          for (IShape s:this.shapeLibrary) {
            if (s.getName().equals(nameOfShape)) {
              s.changeAppearTime(startTime);
              s.changeDisappearTime(endTime);
              s.changeColor(new Color(initialR, initialG, initialB));
              s.changeHeight(initialHeight);
              s.changeWidth(initialWidth);
              s.changeLocation(new Point2D(initialX, initialY));
            }
          }
        }
        this.beginTime = Math.min(this.beginTime, startTime);
        this.finishTime = Math.max(this.finishTime, endTime);
      }
    }
  }

  @Override
  public HashMap<String, ArrayList<IMotion>> getMotionLibrary() {
    return this.motionLibrary;
  }
  
  @Override
  public ArrayList<IShape> getShapeLibrary() {
    return this.shapeLibrary;
  }
  
  @Override
  public ArrayList<IShape> getShapesAtMoment(double time) {
    if (time > this.getFinishTime() || time < this.getBeginTime()) {
      //throw new IllegalArgumentException("Entry time out of range.");
    }
    
    ArrayList<IShape> shapeAtMoment = new ArrayList<IShape>();
    for (IShape shape:this.shapeLibrary) {
      for (IMotion motion : this.motionLibrary.get(shape.getName())) {
        if (time >= motion.getStartTime()
            && time <= motion.getEndTime()) {
          IShape temp = shape;
          double motionStartTime = (double) motion.getStartTime();
          double motionEndTime = (double) motion.getEndTime();
          if (motionStartTime == motionEndTime) {
            temp.changeLocation((Point2D) motion.getEndPoint());
            temp.changeWidth(motion.getEndWidth());
            temp.changeHeight(motion.getEndHeight());
            temp.changeColor((Color) motion.getEndColor());
          } else {
            double status = (double) ((time - motionStartTime)
                / (motionEndTime - motionStartTime));
            temp.changeLocation(
                new Point2D(getStatusAtInt(motion.getInitialPoint().getX(),
                    motion.getEndPoint().getX(), status),
                    getStatusAtInt(motion.getInitialPoint().getY(),
                    motion.getEndPoint().getY(), status)));
            temp.changeWidth(getStatusAtDou(motion.getInitialWidth(), motion.getEndWidth(),
                status));
            temp.changeHeight(getStatusAtDou(motion.getInitialHeight(), motion.getEndHeight(),
                status));
            temp.changeColor(
                new Color(getStatusAtInt(motion.getInitialColor().getRed(),
                    motion.getEndColor().getRed(), status),
                    getStatusAtInt(motion.getInitialColor().getGreen(),
                    motion.getEndColor().getGreen(), status),
                    getStatusAtInt(motion.getInitialColor().getBlue(),
                    motion.getEndColor().getBlue(), status)));
          }
          shapeAtMoment.add(temp);
          continue;
        }
      }
    }
    return shapeAtMoment;
  }
  
  /**
   * Calculate the data for doubles when need status of shape at a given time.
   * @param begin double of start value
   * @param end double of end value
   * @param status double index for helping calculate
   * @return double value
   */
  private double getStatusAtDou(double begin, double end, double status) {
    return begin + (end - begin) * status;
  }
  
  /**
   * Calculate the data for integers when need status of shape at a given time.
   * @param begin integer of start value
   * @param end integer of end value
   * @param status integer index for helping calculate
   * @return integer value
   */
  private int getStatusAtInt(int begin, int end, double status) {
    double newBegin = (double) begin;
    double newEnd = (double) end;
    double result = newBegin + (newEnd - newBegin) * status;
    return (int) result;
  }
  
  @Override
  public void addCanvas(int x, int y, int width, int height) {
    this.canvasX = x;
    this.canvasY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }
  
  @Override
  public int getCanvasX() {
    return this.canvasX;
  }
  
  @Override
  public int getCanvasY() {
    return this.canvasY;
  }
  
  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }
  
  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }
  
  @Override
  public int getBeginTime() {
    return this.beginTime;
  }
  
  @Override
  public int getFinishTime() {
    return this.finishTime;
  }
  
  @Override
  public void clearShapeLibrary() {
    this.shapeLibrary.clear();
    this.motionLibrary.clear();
  }
}

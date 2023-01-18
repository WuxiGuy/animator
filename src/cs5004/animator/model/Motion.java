package cs5004.animator.model;

/**
 * Class motion that stores information about shape during a period.
 * In this period the shape may change color, position and size.
 * @author Yuhao Hua
 *
 */
public class Motion implements IMotion {
  
  private String shapeName;
  private ShapeType shapeType;
  private int startTime;
  private IPoints initP;
  private double initWidth;
  private double initHeight;
  private IColor initColor;
  private int endTime;
  private IPoints endP;
  private double endWidth;
  private double endHeight;
  private IColor endColor;

  /**
   * Constructor that takes information about the motion, then store them.
   * @param nameOfShape String name of shape
   * @param shapeType ShapeType type of shape
   * @param startTime start time of the motion
   * @param initialX start position-x of motion
   * @param initialY start position-y of motion
   * @param initialWidth start width of motion
   * @param initialHeight start height of motion
   * @param initialR start color-red of motion
   * @param initialG start color-green of motion
   * @param initialB start color-blue of motion
   * @param endTime end time of motion
   * @param endX end position-x of motion
   * @param endY end position-y of motion
   * @param endWidth end width of motion
   * @param endHeight end height of motion
   * @param endR end color-red of motion
   * @param endG end color-green of motion
   * @param endB end color-blue of motion
   */
  public Motion(String nameOfShape, ShapeType shapeType, int startTime,
      int initialX, int initialY, double initialWidth, double initialHeight,
      int initialR, int initialG, int initialB,
      int endTime, int endX, int endY, double endWidth, double endHeight,
      int endR, int endG, int endB) {
    this.shapeName = nameOfShape;
    this.shapeType = shapeType;
    this.startTime = startTime;
    this.initP = new Point2D(initialX, initialY);
    this.initWidth = initialWidth;
    this.initHeight = initialHeight;
    this.initColor = new Color(initialR, initialG, initialB);
    this.endTime = endTime;
    this.endP = new Point2D(endX, endY);
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.endColor = new Color(endR, endG, endB);
  }
  
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public IPoints getInitialPoint() {
    return this.initP;
  }

  @Override
  public double getInitialWidth() {
    return this.initWidth;
  }

  @Override
  public double getInitialHeight() {
    return this.initHeight;
  }

  @Override
  public IColor getInitialColor() {
    return this.initColor;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public IPoints getEndPoint() {
    return this.endP;
  }

  @Override
  public double getEndWidth() {
    return this.endWidth;
  }

  @Override
  public double getEndHeight() {
    return this.endHeight;
  }

  @Override
  public IColor getEndColor() {
    return this.endColor;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }
}

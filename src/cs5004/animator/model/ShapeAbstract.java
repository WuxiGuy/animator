package cs5004.animator.model;

/**
 * Class ShapeAbstract includes some common methods for all shapes.
 * @author Yuhao Hua
 *
 */
public abstract class ShapeAbstract implements IShape {
  
  private String shapeName;
  private Point2D position;
  private double width;
  private double height;
  private Color color;
  private int appearTime;
  private int disappearTime;
  
  /**
   * Constructor initialize the shape.
   * @param shapeName String name of shape
   */
  public ShapeAbstract(String shapeName) {
    this.shapeName = shapeName;
  }

  @Override
  public String getName() {
    return this.shapeName;
  }

  @Override
  public Point2D getPosition() {
    return this.position;
  }

  @Override
  public void changeLocation(Point2D location) {
    this.position = location;
  }
  
  @Override
  public double getWidth() {
    return this.width;
  }
  
  @Override
  public double getHeight() {
    return this.height;
  }
  
  @Override
  public void changeWidth(double newW) {
    this.width = newW;
  }
  
  @Override
  public void changeHeight(double newH) {
    this.height = newH;
  }

  @Override
  public Color getColor() {
    return this.color;
  }
  
  @Override
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public void changeAppearTime(int newT) {
    this.appearTime = newT;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void changeDisappearTime(int newT) {
    this.disappearTime = newT;
  }
}

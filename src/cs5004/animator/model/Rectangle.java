package cs5004.animator.model;

/**
 * Class Rectangle represent shape rectangle.
 * @author Yuhao Hua
 *
 */
public class Rectangle extends ShapeAbstract implements IShape {
  
  /**
   * Constructor that initialize shape rectangle.
   * @param shapeName String name of the shape
   */
  public Rectangle(String shapeName) {
    super(shapeName);
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.RECTANGLE;
  }
}

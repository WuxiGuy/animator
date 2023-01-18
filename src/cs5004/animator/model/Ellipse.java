package cs5004.animator.model;

/**
 * Class Ellipse represent shape ellipse.
 * @author Yuhao Hua
 *
 */
public class Ellipse extends Rectangle implements IShape {

  /**
   * Constructor that initializes the shape ellipse by calling superclass
   * which is class Rectangle.
   * @param shapeName String name of the shape
   */
  public Ellipse(String shapeName) {
    super(shapeName);
  }
  
  @Override
  public ShapeType getShapeType() {
    return ShapeType.ELLIPSE;
  }
}

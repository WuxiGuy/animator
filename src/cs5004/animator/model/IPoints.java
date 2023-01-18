package cs5004.animator.model;

/**
 * Interface IPoints represent position of shapes.
 */
public interface IPoints {

  /**
   * Get the x-position of the shape.
   * @return x-position of the class which double
   */
  int getX();
  
  /**
   * Get the y-position of the shape.
   * @return y-position of the class which double
   */
  int getY();
  
  /**
   * Compare if the two points are same.
   * @param aPoint to be compared
   * @return true if the two points are same
   *          false if not
   */
  boolean isEqualTo(IPoints aPoint);
}

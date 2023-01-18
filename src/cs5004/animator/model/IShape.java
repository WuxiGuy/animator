package cs5004.animator.model;

/**
 * Interface IShape represent shapes in model.
 * @author Yuhao Hua
 *
 */
public interface IShape {
  
  /**
   * Get the name of the shape.
   * @return name of the shape
   */
  String getName();

  /**
   * Get the position of the shape.
   * @return position of the class which is class point2D
   */
  Point2D getPosition();
  
  /**
   * Change the location of the shape.
   * @param location of the shape
   */
  void changeLocation(Point2D location);
  
  /**
   * Get the color of the shape.
   * @return color of the shape
   */
  Color getColor();
  
  /**
   * Change the color of the shape.
   * @param newColor of the shape
   */
  void changeColor(Color newColor);
  
  /**
   * Get the time that the shape appears which should not smaller than 1.
   * @return time an integer of the time that the shape appears
   */
  int getAppearTime();
  
  /**
   * Change the time when the shape disappears.
   * @param newT the time when the shape disappears
   */
  void changeAppearTime(int newT);
  
  /**
   * Get the time that the shape disappears which should not smaller than appear time.
   * @return time an integer of the time that the shape disappears
   */
  int getDisappearTime();
  
  /**
   * Change the time when the shape appears.
   * @param newT the time when the shape appears
   */
  void changeDisappearTime(int newT);
  
  /**
   * Get the type of the shape.
   */
  ShapeType getShapeType();
  
  /**
   * Get width of shape.
   * @return double width of shape
   */
  double getWidth();

  /**
   * Get height of shape.
   * @return double height of shape
   */
  double getHeight();

  /**
   * Change width of shape.
   * @param newW new width of shape
   */
  void changeWidth(double newW);

  /**
   * Change height of shape.
   * @param newH new height of shape
   */
  void changeHeight(double newH);
}

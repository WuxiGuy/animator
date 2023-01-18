package cs5004.animator.model;

/**
 * Interface IMotion represent the motion of a shape.
 * @author Yuhao Hua
 *
 */
public interface IMotion {
  
  /**
   * Get the name of the shape.
   * @return String name of shape
   */
  String getShapeName();
  
  /**
   * Get the start time of the motion.
   * @return Integer of start time of motion
   */
  int getStartTime();
  
  /**
   * Get start position of motion.
   * @return IPoint position of motion
   */
  IPoints getInitialPoint();
  
  /**
   * Get start width of shape in motion.
   * @return Double of shape start width in motion
   */
  double getInitialWidth();
  
  /**
   * Get start height of shape in motion.
   * @return Double of shape start height in motion
   */
  double getInitialHeight();
  
  /**
   * Get start color of shape in motion.
   * @return IColor shape start color in motion
   */
  IColor getInitialColor();
  
  /**
   * Get the end time of the motion.
   * @return Integer of end time of motion
   */
  int getEndTime();
  
  /**
   * Get end position of motion.
   * @return IPoint end position of motion
   */
  IPoints getEndPoint();
  
  /**
   * Get end width of shape in motion.
   * @return Double of shape end width in motion
   */
  double getEndWidth();
  
  /**
   * Get end height of shape in motion.
   * @return Double of shape end height in motion
   */
  double getEndHeight();
  
  /**
   * Get end color of shape in motion.
   * @return IColor shape end color in motion
   */
  IColor getEndColor();
  
  /**
   * Get type of shape that the motion belongs to.
   * @return ShapeType type of shape
   */
  ShapeType getShapeType();
}

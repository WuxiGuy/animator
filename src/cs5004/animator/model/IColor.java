package cs5004.animator.model;

/**
 * Interface IColor describes some methods.
 * @author glenn_hyh
 *
 */
public interface IColor {

  /**
   * Get the red color-value.
   * @return red color-value
   */
  int getRed();
  
  /**
   * Get the green color-value.
   * @return green color-value
   */
  int getGreen();
  
  /**
   * Get the blue color-value.
   * @return blue color-value
   */
  int getBlue();

  /**
   * Compare two color to check if they are same.
   * @param aCol a type of color
   * @return true if the two colors are same
   *          false if they are not
   */
  boolean isEqualTo(IColor aCol);
}
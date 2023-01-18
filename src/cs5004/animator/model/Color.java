package cs5004.animator.model;

/**
 * Color class that represent the color index.
 * @author Yuhao Hua
 *
 */
public class Color implements IColor {
  private int red;
  private int green;
  private int blue;
  
  /**
   * Constructor that initialize the information about the color.
   * @param redVal integer of color-red index
   * @param greenVal integer of color-green index
   * @param blueVal integer of color-blue index
   */
  public Color(int redVal, int greenVal, int blueVal) {
    if (redVal < 0 || greenVal < 0 || blueVal < 0
        || redVal > 255 || greenVal > 255 || blueVal > 255) {
      throw new IllegalArgumentException("Color index should not be less than 0.");
    }
    this.red = redVal;
    this.green = greenVal;
    this.blue = blueVal;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public String toString() {
    String output = String.format("(%.1f, %.1f, %.1f)",
        (double) this.red, (double) this.green,(double) this.blue);
    return output;
  }
  
  @Override
  public boolean isEqualTo(IColor aCol) {
    return (this.getRed() == aCol.getRed() && this.getGreen() == aCol.getGreen()
        && this.getBlue() == aCol.getBlue());
  }
}

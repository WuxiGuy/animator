package cs5004.animator.model;

/**
 * Implement interface IPoints.
 */
public class Point2D implements IPoints {
  private int x;
  private int y;
  
  /**
   * build construct.
   * @param positionX index of position-x
   * @param positionY index of position-y
   */
  public Point2D(int positionX, int positionY) {
    this.x = positionX;
    this.y = positionY;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }
  
  @Override
  public String toString() {
    return String.format("(%.1f, %.1f)", (double) this.getX(), (double) this.getY());
  }

  @Override
  public boolean isEqualTo(IPoints aPoint) {
    return (this.getX() == aPoint.getX() && this.getY() == aPoint.getY());
  }
}

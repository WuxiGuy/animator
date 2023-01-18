package cs5004.animator.model;

/**
 * Enum that represents types of shapes.
 * @author Yuhao Hua
 *
 */
public enum ShapeType {
  RECTANGLE, ELLIPSE, FORTEST;
  
  @Override
  public String toString() {
    switch (this) {
      case RECTANGLE:
        return "rectangle";
      case ELLIPSE:
        return "ellipse";
      default:
        throw new IllegalArgumentException("No this type of shape.");
    }
  }
}

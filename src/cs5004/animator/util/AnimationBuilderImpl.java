package cs5004.animator.util;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

/**
 * This class implement AnimationBuilder.
 * @author glenn_hyh
 *
 */
public final class AnimationBuilderImpl
    implements AnimationBuilder<IAnimationModel> {
  
  private IAnimationModel m;
  
  /**
   * Build a construct.
   * @param model animation model
   */
  public AnimationBuilderImpl(IAnimationModel model) {
    this.m = model;
  }

  @Override
  public IAnimationModel build() {
    return this.m;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid Width and height.");
    }
    if (!(x >= 0 && x <= width) || !(y >= 0 || y <= height)) {
      throw new IllegalArgumentException("Invalid position x and y.");
    }
    this.m.addCanvas(x, y, width, height);
  }

  @Override
  public void declareShape(String name, String type) {
    if (type.toLowerCase().equals("rectangle")) {
      this.m.addShape(ShapeType.RECTANGLE, name);
    } else if (type.toLowerCase().equals("ellipse")) {
      this.m.addShape(ShapeType.ELLIPSE, name);
    } else {
      throw new IllegalArgumentException("Invalid shape type!");
    }
    
  }

  @Override
  public void addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    if (t1 > t2) {
      throw new IllegalArgumentException("Start time should not be later than finish time.");
    }
    if (w1 < 0 || w2 < 0 || h1 < 0 || h2 < 0) {
      throw new IllegalArgumentException("Width or height should not be less than 0.");
    }
    if (r1 < 0 || r2 < 0 || g1 < 0 || g2 < 0 || b1 < 0 || b2 < 0) {
      throw new IllegalArgumentException("Color index should not be less than 0.");
    }
    
    for (IShape shape:this.m.getShapeLibrary()) {
      if (shape.getName().equals(name)) {
        this.m.addMotion(name, shape.getShapeType(),
            t1, x1, y1, (double) w1, (double) h1, r1, g1, b1,
            t2, x2, y2, (double) w2, (double) h2, r2, g2, b2);
      }
    }
  }

}

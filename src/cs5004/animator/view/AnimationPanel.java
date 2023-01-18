package cs5004.animator.view;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Class PlayAnimation is a panel that play the animation.
 * @author Yuhao Hua
 *
 */
public class AnimationPanel extends JPanel {
  
  private static final long serialVersionUID = 1L;
  private ArrayList<IShape> shapeList;

  /**
   * Constructor that build and initialize a animation panel.
   * @param shapeList ArrayList of shapes at a moment
   * @param model contains information of animation
   */
  public AnimationPanel(ArrayList<IShape> shapeList, IAnimationModel model) {
    super(true);
    this.shapeList = shapeList;
    setBackground(Color.white);
    this.setLocation(model.getCanvasX(), model.getCanvasY());
    this.setPreferredSize(new Dimension(model.getCanvasWidth() + model.getCanvasX() + 500,
        model.getCanvasHeight() + model.getCanvasY() + 500));
    this.setVisible(true);
  }
  
  /**
   * Information about animation that would be drawn.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    
    if (this.shapeList.isEmpty()) {
      return;
    }
    
    for (IShape shape:this.shapeList) {
      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.draw(new Rectangle2D.Double((double) shape.getPosition().getX(),
            (double) shape.getPosition().getY(), (double) shape.getWidth(),
            (double) shape.getHeight()));
        g2D.fill(new Rectangle2D.Double((double) shape.getPosition().getX(),
            (double) shape.getPosition().getY(), (double) shape.getWidth(),
            (double) shape.getHeight()));
       
      } else if (shape.getShapeType() == ShapeType.ELLIPSE) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.draw(new Ellipse2D.Double((double) shape.getPosition().getX(),
            (double) shape.getPosition().getY(), (double) shape.getWidth(),
            (double) shape.getHeight()));
        g2D.fill(new Ellipse2D.Double((double) shape.getPosition().getX(),
            (double) shape.getPosition().getY(), (double) shape.getWidth(),
            (double) shape.getHeight()));
      }
    }
  }
  
  /**
   * Update ArrayList of shapes at a moment.
   * @param newList new ArrayList of shapes at a moment
   */
  public void updateShapeList(ArrayList<IShape> newList) {
    System.out.print(shapeList.get(0).getColor().getBlue() + "\n");
    this.shapeList = newList;
  }
}

package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Class VisualAnimationView creates frame and visualize the animation.
 * @author Yuhao Hua
 *
 */
@SuppressWarnings("serial")
public class VisualAnimationView extends JFrame implements IAnimationView, ActionListener {
  
  private final AnimationPanel animationPanel;
  private ArrayList<IShape> shapeList;

  /**
   * Constructor that builds the frame for animation.
   * @param m contains information of animation
   */
  public VisualAnimationView(IAnimationModel m) {
    super("Animation");
    
    IAnimationModel model = m;
    this.setPreferredSize(new Dimension(model.getCanvasWidth() 
        + 100, model.getCanvasHeight() + 100));
    this.setLocation(model.getCanvasX(), model.getCanvasY());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout());

    this.animationPanel = new cs5004.animator.view.AnimationPanel((ArrayList<IShape>)
        model.getShapesAtMoment(model.getBeginTime()), model);
    
    this.add(this.animationPanel);
    JScrollPane scrollPane = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(model.getCanvasWidth() + 200,
        model.getCanvasHeight() + 200));
    this.add(scrollPane, BorderLayout.CENTER);
    
    this.pack();
    this.setVisible(true);
  }
  
  @Override
  public void playAnimation() {
    this.animationPanel.updateShapeList(shapeList);
    this.animationPanel.repaint();
  }
  
  @Override
  public void updateShapeList(ArrayList<IShape> newList) {
    this.shapeList = newList;
  }

  @Override
  public void listenAction(AnimationController aController) {
    throw new IllegalArgumentException("Can not use this method for this class.");
  }

  @Override
  public void createFile(String fileName) {
    throw new IllegalArgumentException("No output file for this class.");
  }

  @Override
  public void actionPerformed(ActionEvent args) {
    throw new IllegalArgumentException("No output file for this class.");
  }
}

package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.IShape;

import java.util.ArrayList;

/**
 * Interface IAnimationView represent views that shows
 * information to user.
 * @author Yuhao Hua
 *
 */
public interface IAnimationView {

  /**
   * Listen and send actions to controller.
   * @param aController AnimationController which is a controller
   */
  void listenAction(AnimationController aController);

  /**
   * Display the animation to user.
   */
  void playAnimation();

  /**
   * Output animation by given file name.
   * @param fileName String name of output file.
   */
  void createFile(String fileName);

  /**
   * Update an ArrayList of shapes at a moment.
   * @param newList ArrayList of shapes at a moment
   */
  void updateShapeList(ArrayList<IShape> newList);
}

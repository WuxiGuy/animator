package cs5004.animator.controller;

/**
 * Interface of the controller of the program.
 * @author glenn_hyh
 *
 */
public interface IAnimationController {

  /**
   * Running the program by the chosen view and model.
   */
  void runController();

  /**
   * Play animation based on the chosen view.
   */
  void playVisualView();
}

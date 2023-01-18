package cs5004.animator;

import cs5004.animator.controller.AnimationController;

/**
 * Driver class to send input command line to controller.
 * @author Yuhao Hua
 *
 */
public class EasyAnimator {
  
  /**
   * Main method that takes and sends command line to controller.
   * @param args String of input command line
   */
  public static void main(String[] args) {
    AnimationController c = new AnimationController(args);
    c.runController();
  }
}

package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.IAnimationModel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class PlaybackAnimationView extends class VisualAnimationView that adds
 * operation frame that can control the play of animation.
 * @author Yuhao Hua
 *
 */
@SuppressWarnings("serial")
public class PlaybackAnimationView extends VisualAnimationView
    implements IAnimationView, ActionListener {


  
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton noLoopButton;
  private JButton speedUpButton;
  private JButton speedDownButton;
  private JButton endProgramButton;
  
  /**
   * Constructor that builds operation frame and buttons.
   * @param model animation model
   */
  public PlaybackAnimationView(IAnimationModel model) {
    super(model);
    JFrame frame;
    frame = new JFrame("Operation Panel");
    frame.setSize(500, 150);
    frame.setLocation(0, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel buttonPane;
    buttonPane = new JPanel(true);
    buttonPane.setBackground(Color.white);
    buttonPane.setSize(500, 150);
    buttonPane.setLocation(0, 0);
    buttonPane.setLayout(new FlowLayout());
    
    this.startButton = new JButton("Start");
    this.startButton.setName("start");
    buttonPane.add(this.startButton);
    
    this.pauseButton = new JButton("Pause");
    this.pauseButton.setName("pause");
    buttonPane.add(this.pauseButton);
    
    this.resumeButton = new JButton("Resume");
    this.resumeButton.setName("resume");
    buttonPane.add(this.resumeButton);
    
    this.restartButton = new JButton("Restart");
    this.restartButton.setName("restart");
    buttonPane.add(this.restartButton);
    
    this.loopButton = new JButton("Loop");
    this.loopButton.setName("loop");
    buttonPane.add(this.loopButton);
    
    this.noLoopButton = new JButton("stopLoop");
    this.noLoopButton.setName("stopLoop");
    buttonPane.add(this.noLoopButton);
    
    this.speedUpButton = new JButton("Speed Up");
    this.speedUpButton.setName("speedUp");
    buttonPane.add(this.speedUpButton);
    
    this.speedDownButton = new JButton("Speed Down");
    this.speedDownButton.setName("speedDown");
    buttonPane.add(this.speedDownButton);
    
    this.endProgramButton = new JButton("Terminate");
    this.endProgramButton.setName("terminate");
    buttonPane.add(this.endProgramButton);
    
    frame.add(buttonPane);
    
    this.pack();
    frame.setVisible(true);
  }

  @Override
  public void listenAction(AnimationController aController) {
    this.startButton.addActionListener(aController);
    this.pauseButton.addActionListener(aController);
    this.resumeButton.addActionListener(aController);
    this.restartButton.addActionListener(aController);
    this.restartButton.addActionListener(aController);
    this.loopButton.addActionListener(aController);
    this.noLoopButton.addActionListener(aController);
    this.speedUpButton.addActionListener(aController);
    this.speedDownButton.addActionListener(aController);
    this.endProgramButton.addActionListener(aController);
  }
  
  @Override
  public void playAnimation() {
    super.playAnimation();
  }
}

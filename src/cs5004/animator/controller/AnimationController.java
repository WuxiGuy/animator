package cs5004.animator.controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IAnimationView;
import cs5004.animator.view.PlaybackAnimationView;
import cs5004.animator.view.SvgAnimationView;
import cs5004.animator.view.TextAnimationView;
import cs5004.animator.view.VisualAnimationView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Controller of the whole program. It takes in a view and a model based
 * on the command line sent by EasyAnimator class.
 */
public class AnimationController implements IAnimationController, ActionListener {
  
  private IAnimationView v;
  private IAnimationModel m;
  private double tick;
  private double time;
  private int speed;
  private String output;
  private String viewName;
  private ArrayList<IShape> shapeList;
  private Boolean ifLoop;
  private Boolean ifTerminate;
  
  /**
   * Constructor that deal with the commend received from EasyAnimator.
   * @param args String[] received from EasyAnimator
   */
  public AnimationController(String[] args) {
    try {
      this.m = new AnimationModel();
      readArgs(args);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.time = 0.0;
    this.tick = (double) this.m.getBeginTime();
    this.shapeList = (ArrayList<IShape>) this.m.getShapesAtMoment(tick);
    this.ifLoop = false;
    this.ifTerminate = false;
  }
  
  @Override
  public void runController() {
    
    if (!this.output.equalsIgnoreCase("n/a")) {
      this.v.createFile(this.output);
    }
    
    if (this.viewName.equalsIgnoreCase("playback")) {
      this.v.listenAction(this);
      this.time = 0.0;
      playVisualView();
      
    } else {
      if (this.viewName.equals("text") || this.viewName.equals("svg")) {
        this.v.playAnimation();
      } else if (this.viewName.equals("visual")) {
        this.time = 1.0;
        playVisualView();
      }
    }
    
    this.m.clearShapeLibrary();
  }
  
  @Override
  public void playVisualView() {
    while (tick <= this.m.getFinishTime()) {
      shapeList = (ArrayList<IShape>) this.m.getShapesAtMoment(this.tick);
      this.v.updateShapeList(shapeList);
      this.v.playAnimation();
      this.tick += this.time;
      
      if (this.ifLoop && tick >= this.m.getFinishTime()) {
        this.tick = this.m.getBeginTime();
      }
      
      if (this.tick >= this.m.getFinishTime() && !this.ifTerminate) {
        this.tick = ((double) this.m.getFinishTime()) - 0.1;
        this.time = 0.0;
      }
      
      if (this.ifTerminate) {
        break;
      }
      
      try {
        Thread.sleep((long) ((long) 100 / 10));
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();
    
    switch (c.getName()) {
      case "start":
        this.time = ((double) this.speed) / 10.0;
        break;
      case "pause":
        this.time = 0;
        break;
      case "resume":
        this.time = ((double) this.speed) / 10.0;
        break;
      case "restart":
        this.tick = this.m.getBeginTime();
        this.time = ((double) this.speed) / 10.0;
        break;
      case "loop":
        this.ifLoop = true;
        break;
      case "stopLoop":
        this.ifLoop = false;
        break;
      case "speedUp":
        this.speed = this.speed * 5;
        this.time = ((double) this.speed) / 10.0;
        break;
      case "speedDown":
        this.speed = this.speed / 5;
        if (this.speed == 0) {
          this.speed = 1;
        }
        this.time = ((double) this.speed) / 10.0;
        break;
      case "terminate":
        this.ifTerminate = true;
        break;
      default:
        throw new IllegalArgumentException("No this command.");
    }
  }
  
  /**
   * Read the command line and get the commands of operation to the view
   * and model.
   * @param args String[] of command line
   * @throws FileNotFoundException when input file is not existing
   */
  private void readArgs(String[] args) throws FileNotFoundException {
    this.output = "n/a";
    this.speed = 100;
    this.viewName = "";
    
    try {
      int i = 0;
      while (i < args.length) {
        if (args[i].equalsIgnoreCase("-in")) {
          String inputFile = args[i + 1];
          try {
            AnimationReader.parseFile(new BufferedReader(new FileReader(inputFile)),
                new AnimationBuilderImpl(this.m));
          } catch (FileNotFoundException e1) {
            throw new FileNotFoundException("Can not find the file " + inputFile);
          }
          i += 2;
        } else if (args[i].equalsIgnoreCase("-speed")) {
          this.speed = Integer.parseInt(args[i + 1]);
          i += 2;
        } else if (args[i].equalsIgnoreCase("-out")) {
          this.output = args[i + 1];
          i += 2;
        } else if (args[i].equalsIgnoreCase("-view")) {
          viewName += args[i + 1];
          i += 2;
        }
      }
    } catch (IllegalArgumentException e2) {
      throw new IllegalArgumentException("Invalid args from EasyAnimator.");
    }
    
    buildUpView(viewName);
    
    if (!this.output.equals("n/a")) {
      if (!((this.output.substring(output.length() - 4, output.length()).equalsIgnoreCase(".svg")
          && viewName.equalsIgnoreCase("svg"))
          || (this.output.substring(output.length() - 4, output.length()).equalsIgnoreCase(".txt")
          && viewName.equalsIgnoreCase("text")))) {
        throw new IllegalArgumentException("Selected type of views does not "
            + "support the file to be output.");
      }
    }
  }

  /**
   * This method is to choose the view required in command line.
   * @param view String of the name of the view
   */
  private void buildUpView(String view) {
    if (view.equalsIgnoreCase("svg")) {
      this.v = new SvgAnimationView(this.m, this.speed);
    } else if (view.equalsIgnoreCase("text")) {
      this.v = new TextAnimationView(this.m, this.speed);
    } else if (view.equalsIgnoreCase("visual")) {
      this.v = new VisualAnimationView(this.m);
    } else if (view.equalsIgnoreCase("playback")) {
      this.v = new PlaybackAnimationView(this.m);
    }
  }
}

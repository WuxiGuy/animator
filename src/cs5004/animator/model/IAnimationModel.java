package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Interface of model declares the methods of model.
 * @author Yuhao Hua
 *
 */
public interface IAnimationModel {
  
  /**
   * Create a shape and store the shape.
   * @param shapeType type of shape which is an enum
   * @param shapeName name of the shape
   * @throws IllegalArgumentException when the shape is already existing
   */
  void addShape(ShapeType shapeType, String shapeName)
      throws IllegalArgumentException;
  
  /**
   * Add motion to the certain shape and update the existing time, size,
   * color and position for shape if necessary.
   * @param nameOfShape String name of the shape
   * @param shapeType Enum ShapeType of the shape
   * @param startTime integer of time the motion starts
   * @param initialX integer of the start position-x of the shape
   * @param initialY integer of the start position-y of the shape
   * @param initialWidth double of the start width of the shape
   * @param initialHeight double of the start height of the shape
   * @param initialR integer of start color-red of the shape
   * @param initialG integer of start color-green of the shape
   * @param initialB integer of start color-blue of shape
   * @param endTime integer of time the motion end
   * @param endX integer of end position-x of shape
   * @param endY integer of end position-y of shape
   * @param endWidth integer of end width of shape
   * @param endHeight integer of end height of shape
   * @param endR integer of end color-red of shape
   * @param endG integer of end color-green of shape
   * @param endB integer of end color-blue of shape
   */
  void addMotion(String nameOfShape, ShapeType shapeType, int startTime,
      int initialX, int initialY, double initialWidth, double initialHeight,
      int initialR, int initialG, int initialB, int endTime, int endX,
      int endY, double endWidth, double endHeight,
      int endR, int endG, int endB);
  
  /**
   * Get a hashmap that store all motions.
   * @return HashMap with name of shape as key and ArrayList of motions as value
   */
  HashMap<String, ArrayList<IMotion>> getMotionLibrary();
  
  /**
   * Get an ArrayList that stores all shapes.
   * @return ArrayList that stores all shapes
   */
  ArrayList<IShape> getShapeLibrary();
  
  /**
   * Get a List that stores shapes at a moment of time.
   * @param time double of a moment of time
   * @return List that stores shapes at a moment
   */
  List<IShape> getShapesAtMoment(double time);

  /**
   * Store information about canvas.
   * @param x integer position-x of canvas
   * @param y integer position-y of canvas
   * @param width integer width of canvas
   * @param height integer height of canvas
   */
  void addCanvas(int x, int y, int width, int height);

  /**
   * Get the position-x of canvas.
   * @return integer of position-x of canvas
   */
  int getCanvasX();

  /**
   * Get the position-y of canvas.
   * @return integer of position-y of canvas
   */
  int getCanvasY();

  /**
   * Get the width of canvas.
   * @return integer of width of canvas
   */
  int getCanvasWidth();
  
  /**
   * Get the height of canvas.
   * @return integer of height of canvas
   */
  int getCanvasHeight();

  /**
   * Get the start time of the animation.
   * @return integer of start second of the animation
   */
  int getBeginTime();

  /**
   * Get the end time of the animation.
   * @return integer of end second of the animation
   */
  int getFinishTime();

  /**
   * Clear the information stored for shapes and motions.
   */
  void clearShapeLibrary();
}

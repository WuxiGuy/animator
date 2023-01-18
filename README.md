# EASY ANIMATOR
EsayAnimator is a simple program designed with MVC model that can visualize 
animation in text file to form of text, svg, visual. It also includes a simple
operation panel to control the visual animation.

## Package cs5004.animator

### EasyAnimator
This class include main() method that entry command-line arguments to the program
so that the controller can choose right functions to implement.

Commands includes:
>-in: input the text file including the information needs to be transfered to animation.
>-out: ask program to output the animation to a file and give the name of the output file.
>-view: choose a view to display different forms of animation.
>-speed: determine the speed that the animation plays which should be a positive integer.

## MODEL:
## Package cs5004.animator.model
This package contains the model of the program and help classes that needed to implement
the model function.

### Interface - IAnimationModel
This interface describes the methods that used to implement the model of the program.

#### AnimationModel - implement IanimationModel
This class implement the interface IAnimationModel. This class can create and add shape
and their motions which supports change colors, size and position of the shape. This 
class can also get and save information of the canvas(position-x, position-y, width
and height) of the animation.

This class stores shapes(IShape) in an ArrayList.

This class stores motions(IMotion) in a HashMap which takes name of shape in String as
the keys and ArrayLists that store the motions to certain shaps as values.

### Interface - IShape
This interface describes the methods that used to store information(position, color and
size) of a shape and methods that used to change the shape characters.

#### Abstract - ShapeAbstract - implement IShape
This abstract implements some common methods that are applied to all types of shapes
(rectangle and ellipse).

Methods support get and change position, color, size, appear time and disappear time of
the shape. Also includes name.

#### Rectangle - extends ShapeAbstract
This class extends ShapeAbstract and especially store the type of shape(rectangle).

#### Ellipse - extends Rectangle
This class extends Rectangle and especially store the type of shape(ellipse).

### Interface - IMotion
This interface describes the methods that used to store information of a motion(change
color, position or/and size).

#### Motion - implement IMotion
This class implement interface IMotion that only used to store information including
start and end position, color, size. It also contains the name and type of shape that 
this motion belongs to.

### Interface - IColor
This interface describes the methods that used to store the color information for shape
and motion in the form of (red, green, blue). It also decripes a method that use to
compare if an other color is as same as this color.

#### Color - implement IColor
This class implement interface IColor.

### Interface - IPoints
This interface describes the methods that used to store the position information for shape
and motion. It also decribes a method that use to compare if an other point is as same as
this one.

#### Point2D- implement IPoints
This class implement interface IPoints and represent a 2D point which is (x, y).

## VIEW:
## Package cs5004.animator.view
This package contains the different views for the program.

### Interface - IAnimationView
This interface describes common methods that used by all views(visual, txt, svg and playback).

Common methods include:
>playAnimation(): display the animation to user.
>createFile(): create a certain file for the animation(svg or text).

#### VisualAnimationView - implement IAnimationView - extends JFrame
This view class outputs a visual display of the animation using the JFrame JFC/Swing
component architecture. It reads the text files and transforms them into an animation.

#### AnimationPanel - extends JPanel
This class is to create a panel to be insert to the frame of VisualAnimationView as a 
paintboard to play animation. 

This class takes an ArrayList which stores the shapes which contains the information about
position, size and color at a certain time during the animation.

#### PlaybackAnimationView - extends VisualAnimationView
This class builds an extra frame that includes some button to control the play of visual
animation.

Buttons include:
>Start: start playing the animation.
>Pause: pause the animation.
>Resume: continue playing the animation.
>Restart: replay the animation from begining.
>Loop: loop the animation.
>Stop loop: stop looping animation.
>Speed up: speed up the animation.
>Speed down: speed down the animation.
>Terminate: terminate the playing program.

### TextAnimationView - extends IAnimationView
This class is use to print out the animation in text form or output the animation into 
a text file. 

Importent methods:
>buildShapeText(): create shape information.
>buildMotionText(): create motion information form earliest to the latest.
>createFile: create a text file of animation.

### SVGAnimationView - extends IAnimationView
This class is use to print out the animation in svg form or output the animation into 
a svg file. 

Importent methods:
>buildSVGContent(): firstly create the canvas then create shapes and motions contents by
                    calling method addRectangle()/addEllipse() and addMotion().
>addMotion(): create motion contents following the timeline.

## Controller:
## Package cs5004.animator.controller
This package includes the interface and class of the controller for this program.

## IAnimationController - interface
This interface describe the methods used by controller.

### AnimationController - implement IAnimationController
This class get commands from EasyAnimator then input and send data to model and give
information that the views needs to show user.

According to the command line got from EasyAnimator(readArgs()), controller get the input
file, determine the play speed, choose right view and determine if there is file needed
to be created.

Important methods:
>readArgs(): read and analyze the command line.
>buildUpView(): choose the right view to use and initialize a view.
>playVisualView(): play visual animation by calling methods in visual and playback view.
>actionPerformed(): listen to the information sent by the click on the buttons of operation panel.

## Package cs5004.animator.util
This package has some methods used to help controller analyze input file and convert
to data which are sent to model to use.

#### AnimationReader
This class reads the input file line by line and send the data to builder.

#### AnimationBuilder - interface
This interface describes some methods used to send data to models.

#### AnimationBuilderImpl - implement AnimationBuilder
This class takes data from AnimationReader to set bounds of canvas, declare shapes
and add motions for animation by sending organized data to model.

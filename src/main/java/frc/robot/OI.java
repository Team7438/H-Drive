/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.chenyxVision.AutoRun;
import frc.robot.commands.Autofront;
import frc.robot.commands.DriveBackwardCmd;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.commands.ElevatorDownResetCmd;
import frc.robot.commands.ElevatorRestCmd;
import frc.robot.commands.ElevatorTiltCmd;
import frc.robot.commands.ElevatorWinchCmd;
import frc.robot.commands.FrontDownCmd;
import frc.robot.commands.FrontUpCmd;
import frc.robot.commands.HatchLetGo;
import frc.robot.commands.HatchPickUp;
import frc.robot.commands.HatchSwitch;
import frc.robot.commands.RollinCmd;
import frc.robot.commands.RolloutCmd;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public double bottom = 50;
  public double Hatchpickup = 1120;
  public double HighCargo = 11496;
  public double middle = 7602;
  public double lowcargo = 3825;
  public double cargoshipc = 5000;
  public double midhatch = 000000;
  public double highhatch = 0000000;
  public AutoRun visionCmd = new AutoRun();
  public HatchPickUp VisionHatch = new HatchPickUp();
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  public Joystick joystickZero = new Joystick(0);
  public Joystick joystickOne = new Joystick(1);
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.
  Button trigger = new JoystickButton(joystickZero, 1);
  Button thumb = new JoystickButton(joystickZero, 2);
  public Button three = new JoystickButton(joystickZero, 3);
  public Button four = new JoystickButton(joystickZero, 4);
  public Button five = new JoystickButton(joystickZero, 5);
  public Button six = new JoystickButton(joystickZero, 6);
  public Button seven = new JoystickButton(joystickZero, 7);
  public Button eight = new JoystickButton(joystickZero, 8);
  public Button nine = new JoystickButton(joystickZero, 9);
  public Button ten = new JoystickButton(joystickZero, 10);
  public Button eleven = new JoystickButton(joystickZero, 11);
  public Button twelve = new JoystickButton(joystickZero, 12);

  public Button altone = new JoystickButton(joystickOne, 1);
  public Button alttwo = new JoystickButton(joystickOne, 2);
  public Button altthree = new JoystickButton(joystickOne, 3);
  public Button altfour = new JoystickButton(joystickOne, 4);
  public Button altfive = new JoystickButton(joystickOne, 5);
  public Button altsix = new JoystickButton(joystickOne, 6);
  public Button altseven = new JoystickButton(joystickOne, 7);
  public Button alteight = new JoystickButton(joystickOne, 8);
  public Button altnine = new JoystickButton(joystickOne, 9);
  public Button altten = new JoystickButton(joystickOne, 10);
  public Button alteleven = new JoystickButton(joystickOne, 11);
  public Button alttwelve = new JoystickButton(joystickOne, 12);
  
  //public double joyOneVal = Robot.m_oi.joystickOne.getX();

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

    public OI() {
    // Experimental

    thumb.whileHeld(VisionHatch);
    trigger.cancelWhenPressed(VisionHatch);

    // nine.whenPressed(new RotateCmd(90));
    // Elevator Tilt  -- Now using Shffle Board Buttons
    //eight.whenPressed(new ElevatorTiltCmd(Value.kForward)); //TILT COMMANDS, WORKING
    //ten.whenPressed(new ElevatorTiltCmd(Value.kReverse)); //TILT COMMANDS, WORKING
      
    // HEIGHTS FROM WALPOLE

    // Hatch Pickup From Loading Station: 1120
    // Cargo Ship Cargo: 5000
    // Cargo Ship Hatch Panel: 1120 (Based on Loading Station)


    // Lower Rocket Cargo: 3825
    // Middle Rocket Cargo: 7602
    // High Rocket Cargo: 11496

    // Lower Rocket Hatch: 1120 (Based on Loading Station)
    // Middle Rocket Hatch:
    // High Rocket Hatch: 

    //Intake
    //thumb.whileHeld(new RollinCmd());
    //trigger.whileHeld(new RolloutCmd());
    
    //Front Climber
    //seven.whileHeld(new FrontUpCmd());
    //eight.whileHeld(new FrontDownCmd());

    //Winch Height
    //eleven.whenPressed(new ElevatorWinchCmd(5000));
    //twelve.whenPressed(new ElevatorWinchCmd(500));

    //altnine.whenPressed(new ElevatorRestCmd()); 
    //altone.whenPressed(new ElevatorWinchCmd(4700)); //midhatch 5214
    //altthree.whenPressed(new ElevatorWinchCmd(3100)); //lowcargo
    //altfour.whenPressed(new ElevatorWinchCmd(7130)); //midcargo
    //alttwo.whenPressed(new ElevatorWinchCmd(8438)); //highhatch
    //altsix.whenPressed(new ElevatorWinchCmd(10700));//highcargo
    //altfive.whenPressed(new ElevatorWinchCmd(500)); //Hatchpanel
    //altten.whenPressed(new ElevatorRestCmd());
    //alttwelve.whenPressed(new ElevatorTiltCmd(Value.kForward));
    //altten.whenPressed(new ElevatorTiltCmd(Value.kForward));
    //altseven.whenPressed(visionCmd);
    alteight.cancelWhenPressed(VisionHatch);
    //altseven.whenPressed(new DriveBackwardCmd(5, .4));
    //three.whenPressed(new HatchLetGo());
    altseven.whenPressed(VisionHatch);
    //altOne.whenPressed(new ElevatorWinchCmd(1000)); //CHANGE TO CALCULATED VALUE HEIGHTS 
    //altTwo.whenPressed(new ElevatorWinchCmd(2500));
    //altThree.whenPressed(new ElevatorWinchCmd(2750));
    /* //second joystick
    altnine.whenPressed(new ElevatorWinchCmd(5214)); //midhatch
    alteight.whenPressed(new ElevatorWinchCmd(3100)); //lowcargo
    altten.whenPressed(new ElevatorWinchCmd(7130)); //midcargo
    alteleven.whenPressed(new ElevatorWinchCmd(8938)); //highhatch
    alttwelve.whenPressed(new ElevatorWinchCmd(10700));//highcargo
    altseven.whenPressed(new ElevatorWinchCmd(1000)); //Hatchpanel
    */
    

    //joyOneVal = Robot.m_oi.joystickOne.getX();
    //if (joyOneVal <= 0.1 && joyOneVal >= -0.1) {
    //    Robot.elevatorWinch.stop();
    //} else if (joyOneVal >= 0.9) {
    //    System.out.println("Rolling Up w/ Arcade Joystick");
    //    Robot.elevatorWinch.rollUpSlow();
    //} else if (joyOneVal <= -0.9) {
    //    Robot.elevatorWinch.rollDownSlow();
    //    System.out.println("Rolling Down w/ Arcade Joystick");
    //}
    

    /*  COMMANDS
        Buttons - 13
      FrontUpCmd() - Climbing, lowers climbing legs
      FrontDownCmd() - Climbing, raises climbing legs
      ElevatorWinchCmd(0) - Raises elevator to said calculated height
        ElevatorWinchCmd(0) -1 Lowest
        ElevatorWinchCmd(0) -2
        ElevatorWinchCmd(0) -3
        ElevatorWinchCmd(0) -4
        ElevatorWinchCmd(0) -5
        ElevatorWinchCmd(0) -6
        ElevatorWinchCmd(0) -7
        ElevatorWinchCmd(0) -8
        ElevatorWinchCmd(0) -9     
      ElevatorTiltCmd(Value.kForward) - Tilts elevator forward
      ElevatorTiltCmd(Value.kReverse) - Tilts elevator backward
      eleven.whenPressed(new RotateCmd(25) - Rotates robot said degrees
      PushoutCmd() - Ejects the hatch panel
    */
    
    // Rotate and Drive Commands
    //eleven.whenPressed(new RotateCmd(90)); //Use these for testing
    //twelve.whenPressed(new RotateCmd(-90)); 
    //twelve.whenPressed(new DriveBackwardCmd(2,.5));
    
    // Hatch Release Command
    //six.whenPressed(new HatchSwitch());

    // PLEASE KEEP THIS COMMENTED FOR NOW

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());
  }
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

  //Drive Motors
  public static int left1 = 2;
  public static int left2 = 3;
  public static int right1 = 0;
  public static int right2 = 1;
  public static int center = 9;
  
  //Intake Motors
  public static int armLeft = 4;
  public static int armRight = 5;

  //Climb Motors
  public static int climbFront = 6;

  //Elevator Motor
  public static int elevator = 7;

  //Drive Encoders
  public static int rightEncoderPort1 = 0;
  public static int rightEncoderPort2 = 1;
  public static int leftEncoderPort1 = 2;
  public static int leftEncoderPort2 = 3;

  //Elevator Winch Encoder
  public static int elevatorEncoderPort1 = 4;
  public static int elevatorEncoderPort2 = 5;

  //Limit Switches
  public static int intakeLimitPort = 7;
  public static int elevatorLimitPort = 8;

  //Solenoid Valves for Pneumatics
  public static int eleSole1 = 1;
  public static int eleSole2 = 0;
  public static int hatchSole3 = 3;
}

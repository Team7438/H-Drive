/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorTune extends Command {
    int joyPOVVal;
    double joyOneVal;
  public ElevatorTune() {
    // Use requires() here to declare subsystem dependencies

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("owo");
    joyPOVVal = Robot.m_oi.joystickZero.getPOV();
    if (joyPOVVal == -1) {
        //Robot.elevatorWinch.stop();
    } else if (joyPOVVal < 90 || joyPOVVal > 270) {
        System.out.println("Rolling Up w/ Main Directional Joystick");
        //Robot.elevatorWinch.rollUpSlow();
    } else if (joyPOVVal > 90 && joyPOVVal < 270) {
        System.out.println("Rolling Down w/ Main Directional Joystick");
        //Robot.elevatorWinch.rollDownSlow();
    }
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
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

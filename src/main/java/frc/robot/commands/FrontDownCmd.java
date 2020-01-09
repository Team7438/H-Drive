/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.Console;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.FrontClimber;

public class FrontDownCmd extends Command {
  public FrontDownCmd() {
    // requires(Robot.frontClimber);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.frontClimber.FrontDown();
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
    //return Robot.limitSwitch.get();
    //return Robot.cargoLoader.isSwitchSet();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    FrontClimber.frontLift.stopMotor();
    // Console.WriteLine("Text to print");

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    FrontClimber.frontLift.stopMotor();
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorWinch;
// import frc.robot.subsystems.PIDElevator;

public class ElevatorWinchCmd extends Command {
  double travelDistanceup;
  double travelDistancedown;
  double desiredHeight;
  int timer = 0;
  
  public ElevatorWinchCmd(double height) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.elevatorWinch);
    travelDistanceup = height+15;
    travelDistancedown = height-15;
    desiredHeight = height;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
         //Robot.elevatorWinch.ElevatorEncoderReset();
         timer = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    timer += 1;
    //Robot.elevatorWinch.eleEncoderUpdate();
    if(ElevatorWinch.height < travelDistancedown){
      //Robot.elevatorWinch.rollUp();
    }else if(ElevatorWinch.height > travelDistanceup) {
      //Robot.elevatorWinch.rollDown();
    }else if (desiredHeight != 0) {
      //Robot.elevatorWinch.stop();
    }else {
      ElevatorWinch.elevatorWinch.setSpeed(0);
    }
    
  }
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean undershot = ElevatorWinch.height < travelDistancedown;
    boolean overshot = ElevatorWinch.height > travelDistanceup;
    boolean isFinished = !undershot && !overshot && desiredHeight != 0;

    // if(ElevatorWinch.elevatorEncoder.getRaw() >= travelDistance && travelDistance > 0){
    //   return true;
    //   } else if(ElevatorWinch.elevatorEncoder.getRaw() <= travelDistance && travelDistance < 0){

    //   return true;
    //   }else{
    //     return false;
    //   }
    /*if (Robot.m_oi.joystickZero.getPOV() == -1) {
      return false;
    } else {
      return true;
    }
    */
    return isFinished;
  }


  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.elevatorWinch.stop();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // Robot.elevatorWinch.stop();

  }
}

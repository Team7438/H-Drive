/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FrontClimber extends Subsystem {
  public static PWMVictorSPX frontLift = new PWMVictorSPX(RobotMap.climbFront);
  // public static DigitalInput limitSwitch = new DigitalInput(RobotMap.limit);
  // Counter counter = new Counter(limitSwitch);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
    //public boolean isSwitchSet(){
    //  return limitSwitch.get();
    //}
    // public boolean isSwitchSet() {
    //   return counter.get() > 0;
    //}

  public void FrontDown(){
    frontLift.setSpeed(0.8);
    }

  public void FrontUp(){
    frontLift.setSpeed(-0.4); 
    }

  // public boolean isswitchset(){
  //   return limitswitch.get();
  // }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

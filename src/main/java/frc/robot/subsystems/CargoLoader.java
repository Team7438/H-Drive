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
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class CargoLoader extends Subsystem {
  public static PWMVictorSPX leftArm = new PWMVictorSPX(RobotMap.armLeft);
  public static PWMVictorSPX rightArm = new PWMVictorSPX(RobotMap.armRight);
  public static DigitalInput limitSwitch = new DigitalInput(RobotMap.intakeLimitPort);
  // Counter counter = new Counter(limitSwitch);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
    public boolean isSwitchSet(){
      return limitSwitch.get();
}
  // public boolean isSwitchSet() {
  //   return counter.get() > 0;
  // }

  public void Rollin(){
    leftArm.setSpeed(0.5);  //was 0.3, testing using controller for front climbing motor
    //rightArm.setSpeed(0.3);

  }

  public void Rollout(){
    leftArm.setSpeed(-0.8); //was -0.6, testing using controller for front climbing motor
    //rightArm.setSpeed(-0.6);
  }

  public void RollStop(){
    leftArm.setSpeed(0); 
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

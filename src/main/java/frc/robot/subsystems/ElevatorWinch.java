/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ElevatorWinch extends Subsystem {
  public static PWMVictorSPX elevatorWinch = new PWMVictorSPX(RobotMap.elevator);
  public static Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderPort1, RobotMap.elevatorEncoderPort2);
  public static DigitalInput elevatorLimitSwitch  = new DigitalInput(RobotMap.elevatorLimitPort);
  public static double setHeight; 
  public static double height;
  public int CurrentHeight;
  public static double secondLevelHeight = 2500;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void ElevatorEncoderInit(){
    elevatorEncoder.setDistancePerPulse(1); //CHANGE WHEN INSTALL THEM
		elevatorEncoder.setMaxPeriod(.1);
		elevatorEncoder.setMinRate(10);
		elevatorEncoder.setReverseDirection(false);
  }

  public void ElevatorEncoderReset(){
    elevatorEncoder.reset();
  }
  public int GetCurrentHeight(){
    return elevatorEncoder.getRaw();
  }
  public void eleEncoderUpdate(){
    //height = (elevatorEncoder.getRaw()/240)*(Math.PI * 1.25); // In inches
    height = elevatorEncoder.getRaw(); // - RAW for testing 2/10/19 Encoder not working

    SmartDashboard.putNumber("Height", height);
  }

  public boolean elevatorLimitPressed() {
		return !elevatorLimitSwitch.get();
	}
	
	public void updateElevatorStatus() {
    SmartDashboard.putBoolean("Elevator Limit", elevatorLimitPressed());
		if (elevatorLimitPressed()) {
      ElevatorEncoderReset();
      // while (elevatorEncoder.getRaw() <= 20) { //was0
      //   elevatorWinch.setSpeed(.3);
      // }
      elevatorWinch.setSpeed(.3);
      System.out.println("*** Elevator Limit Switch Pressed ***");
		}
}

  public void rollUp(){
    elevatorWinch.setSpeed(1); //+ stopPower(height)); was .6, 
  }
  public void rollDown(){
    elevatorWinch.setSpeed(-.6); // + stopPower(height)); //was -.2
  }
  public void stop(){
    elevatorWinch.setSpeed(stopPower(height));

  }

  public void rollUpSlow(){
    elevatorWinch.setSpeed(1); //was 0.6
  }
  public void rollDownSlow() {
    elevatorWinch.setSpeed(-.6); //was -0.2
  }

  public double stopPower(double height) {

    if(height > secondLevelHeight){
      return (.1); //AJUST UNTIL STOPS was .2 2/17/19
    }else{
      return(.07); //SAME HERE was .16 2/17/19
    }

    //return 0;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new ElevatorWinchCmd());
  }
}

package frc.robot.chenyxVision;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.chenyxVision.libs.*;

/**
 * MANDATORY: For thread safty, use only the public methods/variables unless you know what you are doing
 * MANDATORY: Follow math unit circle convention, turning right should always DECREASE the theta angle
 * 
 * Almost a direct copy of 254's code to calculate robot's current position to the field
 */
public class RobotState implements Runnable{
    private static RobotState instance;
    public static RobotState getInstance() {
        if(instance==null)
            synchronized(RobotState.class){
                if(instance==null)
                    instance=new RobotState();
            }
        return instance;
    }
    private RobotState(){
        Pose startPose=new Pose(0,0,90);
        /*
        pigeon=new PigeonIMU(Robot.climber.masterTalon); //change it if using navx
        pigeon.setFusedHeading(startPose.theta);
        */
        poseHistory=new InterpolatingTreeMap<InterpolatingDouble, Pose>(Constants.MAX_HISTORY_SIZE);
        addObservation(startPose);
        lastL=Robot.driveSub.m_encoderLeft.getDistance();
        lastR=Robot.driveSub.m_encoderRight.getDistance(); //implement these two functions
        notifier=new Notifier(this);
        notifier.startPeriodic(Constants.ROBOT_STATE_PERIOD);
    }
//    private PigeonIMU pigeon;
    private InterpolatingTreeMap<InterpolatingDouble, Pose> poseHistory;
    private double lastL, lastR;
    private Notifier notifier;

    @Override
    public void run(){
        Pose pose=getPose();
        double theta=-Robot.driveSub.Gyro.getAngle()+90; // implement something similar
        Robot.driveSub.encoderUpdate();
        double dl=Robot.driveSub.distanceLeft-lastL,
            dr=Robot.driveSub.distanceRight-lastR,
            ds=(dl+dr)/2;
        addObservation(new Pose(pose.x+ds*Math.cos(Math.toRadians(theta)),
            pose.y+ds*Math.sin(Math.toRadians(theta)),theta));
        lastL+=dl;
        lastR+=dr;
    }

    private synchronized void addObservation(Pose pose){
        poseHistory.put(new InterpolatingDouble(Timer.getFPGATimestamp()), pose);
    }
    public synchronized Pose getPose(double timestamp){
        return poseHistory.getInterpolated(new InterpolatingDouble(timestamp));
    }
    public synchronized Pose getPose(){
        return poseHistory.lastEntry().getValue();
    }

    private class RobotStateStartTwiceException extends Exception{
    }
}
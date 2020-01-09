package frc.robot.chenyxVision;

import frc.robot.chenyxVision.libs.Pt;
import frc.robot.chenyxVision.libs.Vector;

public class Constants{
    // the vector from the driving camera to the center of robot
    public static final Pt CAMERA_OFFSET = new Pt(-9.4, 15);
    // localization and vision constants
    public static final double VISION_DISTANCE_CONSTANT = 211 / 25.4; //actualDis*imageHeight (mm*px)
    public static final int MAX_HISTORY_SIZE = 150;
    public static final int HUD_WIDTH=320;
    public static final int HUD_HEIGHT=240;
    public static final double HUD_HORIZONTAL_FOV=63;
    public static final double ROBOT_STATE_PERIOD=0.01;
    public static final double LOCK_ERROR=190 / 25.4; //mm
    public static final double HUD_MAX_SHOWN_DISTANCE=5000 / 25.4; //mm
    public static final double VISION_DISCONNECT_TIME=2;

    //auto constants
    public static final double MIN_TURN_SPEED=0.4; //rotational pwr, TODO tune this |was 0.08
    public static final double MAX_ALLOWED_ANGLE_ERROR = 2; //deg

    public static final double SHOOT_DIS=1151 / 25.4; //mm, TODO tune this
    public static final double CLOSE_DIS=SHOOT_DIS + 700/25.4; //mm, TODO tune this according to above
    public static final double START_ADDITION_SPEED=0.5; //linear pwr
    public static final double CLOSE_SPEED=0.30; //linear pwr
    public static final double CLOSE_ANGULAR_ERROR_PENALTY=0.0135; // deg/linear_pwr

    public static final double PHYSICAL_OFFSET=-9.4; // adjust it if camera is not centered
    public static final double OFFSET_THRESH=15; 
    public static final double OFFSET_COMP=0; // use only if you delivery system requires
}
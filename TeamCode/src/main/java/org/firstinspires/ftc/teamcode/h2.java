package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "h2", group = "")
public class h2 extends LinearOpMode {

  private DcMotor left_motor;
  private DcMotor right_motor;


  @Override
  public void runOpMode() {
    // Assigning the variables
    double leften;
    double righten;
    double difference;
    double time;
    double power;
    power = 0;
    difference= 0;

    left_motor = hardwareMap.dcMotor.get("left_motor");
    right_motor = hardwareMap.dcMotor.get("right_motor");

    // Initializations
    left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    left_motor.setTargetPosition(0);
    right_motor.setTargetPosition(0);
    waitForStart();
    if (opModeIsActive()) {
      // Put run bl
      while (true) {
        if (power > 1) {
          break;
        } else {
          //Reseting the whole motors and time
          left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
          right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
          left_motor.setPower(power);
          right_motor.setPower(power);
          time = 0;
          // yes
          while (!(time > 60)) {
            sleep(5000);
            leften = left_motor.getCurrentPosition();
            righten = right_motor.getCurrentPosition();
            if (leften > righten) {
              difference = leften - righten;
            } else if (righten > leften) {
              difference = righten - leften;
            }
            // Giving a update on the time power and encoder differences on the phone
            telemetry.addData("Power =", power);
            telemetry.addData("Time Done =", time);
            telemetry.addData("Left =", leften);
            telemetry.addData("Right =", righten);
            telemetry.addData("Difference =", difference);
            telemetry.update();
            // Incrementing the time because of the sleep
            time += 5;
          }
          // Incrementing variable for power + 0.1 each minute
          power += 0.1;
        }
      }
      // Resetting the motor to 0 position
      left_motor.setPower(0);
      right_motor.setPower(0);
    }
  }
}

package com.vip.mesos;

import java.io.IOException;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Scheduler;

import com.groupon.mesos.JesosSchedulerDriver;

public class FrameworkExam {

  public static void main(String[] args) {
    FrameworkInfo.Builder builder = FrameworkInfo.newBuilder();
    builder.setFailoverTimeout(0.0d);
    builder.setUser("");
    builder.setRole("*");
    builder.setCheckpoint(false);
    builder.setName("framework-example");

    Scheduler scheduler = new SchedulerExam();
    // SchedulerDriver driver = new MesosSchedulerDriver(scheduler, builder.build(),
    // "zk://");
    // int status = driver.run() == Protos.Status.DRIVER_STOPPED ? 0 : 1;
    // driver.stop();
    // System.exit(status);
    try {
      JesosSchedulerDriver driver = new JesosSchedulerDriver(scheduler, builder.build(),
          "zk://");
      int status = driver.run() == Protos.Status.DRIVER_STOPPED ? 0 : 1;
      driver.stop();
      System.exit(status);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

package com.vip.mesos;

import java.util.ArrayList;
import java.util.List;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Scheduler;
import org.apache.mesos.SchedulerDriver;


public class SchedulerExam implements Scheduler {


  @Override
  public void registered(SchedulerDriver schedulerDriver, Protos.FrameworkID frameworkID, Protos.MasterInfo masterInfo) {}

  @Override
  public void reregistered(SchedulerDriver schedulerDriver, Protos.MasterInfo masterInfo) {}

  @Override
  public void resourceOffers(SchedulerDriver schedulerDriver, List<Protos.Offer> offers) {
    for (Protos.Offer offer : offers) {
      List<TaskInfo> tasks = new ArrayList<TaskInfo>();
      List<OfferID> offerIds = new ArrayList<OfferID>();
      String id = "task" + System.currentTimeMillis();
      Protos.TaskInfo task = Protos.TaskInfo.newBuilder()
          .setName(id)
          .setTaskId(Protos.TaskID.newBuilder().setValue(id))
          .setSlaveId(offer.getSlaveId())
          .addResources(Protos.Resource.newBuilder()
              .setName("cpus")
              .setType(Protos.Value.Type.SCALAR)
              .setScalar(Protos.Value.Scalar.newBuilder().setValue(1.0))
              .setRole("*").build())
          .setCommand(Protos.CommandInfo.newBuilder().setValue("/bin/echo hello"))
          .build();
      tasks.add(task);
      offerIds.add(offer.getId());
      schedulerDriver.launchTasks(offerIds, tasks);
    }

  }


  @Override
  public void offerRescinded(SchedulerDriver schedulerDriver, Protos.OfferID offerID) {}

  @Override
  public void statusUpdate(SchedulerDriver driver, Protos.TaskStatus taskStatus) {
    System.out.println("received status update" + taskStatus);
  }

  @Override
  public void frameworkMessage(SchedulerDriver schedulerDriver, Protos.ExecutorID executorID, Protos.SlaveID slaveID, byte[] bytes) {}

  @Override
  public void disconnected(SchedulerDriver schedulerDriver) {}

  @Override
  public void slaveLost(SchedulerDriver schedulerDriver, Protos.SlaveID slaveID) {}

  @Override
  public void executorLost(SchedulerDriver schedulerDriver, Protos.ExecutorID executorID, Protos.SlaveID slaveID, int i) {}

  @Override
  public void error(SchedulerDriver schedulerDriver, String s) {}
}

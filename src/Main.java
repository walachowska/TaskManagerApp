import Controller.Logger;
import Controller.mediator.Mediator;
import Controller.mediator.LogMediator;
import Model.builders.CheckListTaskBuilder;
import Model.builders.DeadlineTaskBuilder;
import Model.builders.PriorityTaskBuilder;
import Model.tasks.Priority;
import Model.tasks.TaskWithCheckList;
import Model.tasks.TaskWithDeadline;
import Model.tasks.TaskWithPriority;
import View.TaskManagerGUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("completedTasks.txt");
        Mediator mediator = new LogMediator(logger);

        DeadlineTaskBuilder deadlineBuilder = new DeadlineTaskBuilder();
        TaskWithDeadline deadlineTask = deadlineBuilder
                .newTask()
                .setName("deadline task")
                .setDescription("description")
                .setDeadline(LocalDate.parse("2023-01-15"))
                .setMediator(mediator)
                .getResult();
        deadlineTask.markAsCompleted();

        CheckListTaskBuilder checkListTaskBuilder = new CheckListTaskBuilder();
        TaskWithCheckList checklistTask = checkListTaskBuilder
                .newTask()
                .setName("check list task")
                .setDescription("description")
                .setChecklist(new ArrayList<>(Arrays.asList(
                        "sub 1",
                        "sub 2"
                )))
                .setMediator(mediator)
                .getResult();
        checklistTask.markAsCompleted();

        PriorityTaskBuilder priorityTaskBuilder = new PriorityTaskBuilder();
        TaskWithPriority priorityTask = priorityTaskBuilder
                .newTask()
                .setName("priority task")
                .setDescription("desc")
                .setPriority(Priority.HIGH)
                .setMediator(mediator)
                .getResult();
        priorityTask.markAsCompleted();

        new TaskManagerGUI(mediator);
    }

}
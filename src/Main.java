import Controller.Logger;
import Controller.mediator.Mediator;
import Controller.mediator.LogMediator;
import Model.builders.DeadlineTaskBuilder;
import Model.tasks.TaskWithDeadline;
import View.TaskManagerGUI;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("completedTasks.txt");
        Mediator mediator = new LogMediator(logger);

        /*DeadlineTaskBuilder deadlineBuilder = new DeadlineTaskBuilder();
        TaskWithDeadline deadlineTask = deadlineBuilder.setName("Zadanie 1")
                .setDescription("pierwsze zadanie moje")
                .setDeadline(LocalDate.parse("2023-01-15"))
                .getResult();
        deadlineTask.setMediator(mediator);
        deadlineTask.markAsCompleted();*/

        new TaskManagerGUI(mediator);
    }

}
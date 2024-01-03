import Controller.Logger;
import Controller.mediator.Mediator;
import Controller.mediator.TaskMediator;
import Model.builders.DeadlineTaskBuilder;
import Model.tasks.TaskWithDeadline;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("historyOfTasks.txt");
        Mediator mediator = new TaskMediator(logger);

        DeadlineTaskBuilder deadlineBuilder = new DeadlineTaskBuilder();
        TaskWithDeadline deadlineTask = deadlineBuilder.setName("Zadanie 1")
                .setDescription("pierwsze zadanie moje")
                .setDeadline(LocalDate.parse("2023-01-15"))
                .getResult();
        deadlineTask.setMediator(mediator);
        deadlineTask.markAsCompleted();
    }

}
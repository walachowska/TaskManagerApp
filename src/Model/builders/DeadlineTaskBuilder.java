package Model.builders;

import Controller.mediator.Mediator;
import Model.tasks.TaskWithDeadline;
import java.time.LocalDate;

public class DeadlineTaskBuilder implements Builder{
    private TaskWithDeadline task;
    private static final String deadlineHashtag = "task with deadline";

    public DeadlineTaskBuilder setName(String name) {
        this.task.setName(name);
        return this;
    }

    @Override
    public DeadlineTaskBuilder setDescription(String description) {
        this.task.setDescription(deadlineHashtag + "\n" + description);
        return this;
    }

    public DeadlineTaskBuilder setDeadline(LocalDate deadline){
        this.task.setDeadline(deadline);
        return this;
    }

    @Override
    public DeadlineTaskBuilder setMediator(Mediator mediator) {
        this.task.setMediator(mediator);
        return this;
    }

    public DeadlineTaskBuilder newTask(){
        this.task = new TaskWithDeadline();
        return this;
    }

    public TaskWithDeadline getResult(){
        return this.task;
    }
}

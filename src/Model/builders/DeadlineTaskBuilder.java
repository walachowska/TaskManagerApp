package Model.builders;

import Model.tasks.TaskWithDeadline;
import java.time.LocalDate;

public class DeadlineTaskBuilder implements Builder{
    private TaskWithDeadline task;
    private static final String deadlineHashtag = "task with deadline";

    public DeadlineTaskBuilder(){
        this.task = new TaskWithDeadline();
    }
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
    public void resetTask(){
        this.task = new TaskWithDeadline();
    }

    public TaskWithDeadline getResult(){
        return this.task;
    }
}

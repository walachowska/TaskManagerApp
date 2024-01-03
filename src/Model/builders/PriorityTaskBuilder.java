package Model.builders;

import Model.tasks.Priority;
import Model.tasks.TaskWithPriority;
import java.time.LocalDate;

public class PriorityTaskBuilder implements Builder {
    private TaskWithPriority task;
    private static final String priorityHashtag = "task with priority";

    public PriorityTaskBuilder(){
        this.task = new TaskWithPriority();
    }
    @Override
    public Builder setName(String name) {
        this.task.setName(name);
        return this;
    }

    @Override
    public Builder setDescription(String description) {
        this.task.setDescription(priorityHashtag + "\n" + description);
        return this;
    }

    public Builder setPriority(Priority priority){
        this.task.setPriority(priority);
        return this;
    }

    @Override
    public void resetTask() {
        this.task = new TaskWithPriority();
    }

    public TaskWithPriority getResult(){
        return this.task;
    }
}

package Model.builders;

import Model.tasks.Priority;
import Model.tasks.TaskWithPriority;
import java.time.LocalDate;

public class PriorityTaskBuilder implements Builder {
    private TaskWithPriority task;
    private static final String priorityHashtag = "task with priority";

    @Override
    public PriorityTaskBuilder setName(String name) {
        this.task.setName(name);
        return this;
    }

    @Override
    public PriorityTaskBuilder setDescription(String description) {
        this.task.setDescription(priorityHashtag + "\n" + description);
        return this;
    }

    public PriorityTaskBuilder setPriority(Priority priority){
        this.task.setPriority(priority);
        return this;
    }

    public PriorityTaskBuilder resetTask() {
        this.task = new TaskWithPriority();
        return this;
    }

    public TaskWithPriority getResult(){
        return this.task;
    }
}

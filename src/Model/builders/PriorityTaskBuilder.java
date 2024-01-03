package builders;

import Model.Priority;
import Model.TaskWithPriotity;
import java.time.LocalDate;

public class PriorityTaskBuilder implements Builder {
    private TaskWithPriotity task;
    private static final String priorityHashtag = "task with priority";

    public PriorityTaskBuilder(){
        this.task = new TaskWithPriotity();
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

    @Override
    public Builder setDateOfCompletion(LocalDate dateOfCompletion) {
        this.task.setDateOfCompletion(dateOfCompletion);
        return this;
    }

    public Builder setPriority(Priority priority){
        this.task.setPriority(priority);
        return this;
    }

    @Override
    public void resetTask() {
        this.task = new TaskWithPriotity();
    }

    public TaskWithPriotity getResult(){
        return this.task;
    }
}

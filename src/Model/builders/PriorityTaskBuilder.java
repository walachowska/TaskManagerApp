package Model.builders;

import Controller.mediator.Mediator;
import Model.tasks.Priority;
import Model.tasks.TaskWithPriority;

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

    @Override
    public PriorityTaskBuilder setMediator(Mediator mediator) {
        this.task.setMediator(mediator);
        return this;
    }

    public PriorityTaskBuilder newTask() {
        this.task = new TaskWithPriority();
        return this;
    }

    public TaskWithPriority getResult(){
        return this.task;
    }
}

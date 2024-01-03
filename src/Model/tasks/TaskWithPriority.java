package Model.tasks;

import Controller.mediator.Mediator;
import Model.memento.PriorityTaskMemento;

import java.time.LocalDate;

public class TaskWithPriority extends Task{
    private Priority priority;
    private Mediator mediator;

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Priority getPriority() {
        return priority;
    }

    public void setMediator(Mediator mediator){
        this.mediator=mediator;
    }

    public void markAsCompleted() {
        if (mediator != null) {
            this.setDateOfCompletion(LocalDate.now());
            mediator.logEvent("Task: " + this.getName() +" with priority " +
                    " completed on " + this.getDateOfCompletion());
        }
    }

    public PriorityTaskMemento saveMemento() {
        return new PriorityTaskMemento(getName(), getDescription(), getDateOfCompletion(), priority);
    }

    public void restoreFromMemento(PriorityTaskMemento memento) {
        this.setName(memento.getName());
        this.setDescription(memento.getDescription());
        this.setDateOfCompletion(memento.getDateOfCompletion());
        this.priority = memento.getPriority();

    }
}

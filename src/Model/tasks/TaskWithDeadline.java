package Model.tasks;

import Controller.mediator.Mediator;
import Model.memento.DeadlineTaskMemento;
import Model.memento.TaskMemento;

import java.time.LocalDate;

public class TaskWithDeadline extends Task{
    private LocalDate deadline;
    private Mediator mediator;
    private DeadlineTaskMemento memento;
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if(!deadline.isAfter(LocalDate.now())){
            System.out.println("The deadline should be a future date. Please insert correct date.");
        }
        this.deadline = deadline;
    }
    public void setMediator(Mediator mediator){
        this.mediator=mediator;
    }

    public void markAsCompleted() {
        if (mediator != null) {
            this.setDateOfCompletion(LocalDate.now());
            mediator.logEvent("Task: " + this.getName() + " completed on " + this.getDateOfCompletion() +
                    " with deadline: " + this.deadline);
        }
    }

    public void saveMemento() {
        memento = new DeadlineTaskMemento(getName(), getDescription(), getDateOfCompletion(), deadline);
    }

    public void restoreFromMemento() {
        this.setName(memento.getName());
        this.setDescription(memento.getDescription());
        this.setDateOfCompletion(memento.getDateOfCompletion());
        this.deadline = memento.getDeadline();

    }
}


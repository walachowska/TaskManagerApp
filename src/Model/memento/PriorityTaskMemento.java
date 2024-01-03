package Model.memento;

import Model.tasks.Priority;

import java.time.LocalDate;

public class PriorityTaskMemento extends TaskMemento{
    private final Priority priority;
    public PriorityTaskMemento(String name, String description, LocalDate dateOfCompletion, Priority priority) {
        super(name, description, dateOfCompletion);
        this.priority = priority;
    }

    public Priority getPriority(){
        return this.priority;
    }
}

package Model.memento;

import java.time.LocalDate;

public class TaskMemento {
    private final String name;
    private final String description;
    private final LocalDate dateOfCompletion;

    public TaskMemento(String name, String description, LocalDate dateOfCompletion) {
        this.name = name;
        this.description = description;
        this.dateOfCompletion = dateOfCompletion;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }
}

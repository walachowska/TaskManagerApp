package Model.tasks;

import Model.memento.TaskMemento;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private String description;
    private LocalDate dateOfCompletion;

    public Task() {
        this.id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }
}

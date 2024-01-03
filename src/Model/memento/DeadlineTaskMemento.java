package Model.memento;

import java.time.LocalDate;

public class DeadlineTaskMemento extends TaskMemento {
    private final LocalDate deadline;

    public DeadlineTaskMemento(String name, String description, LocalDate dateOfCompletion, LocalDate deadline) {
        super(name, description, dateOfCompletion);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
}

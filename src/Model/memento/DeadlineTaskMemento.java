package Model.memento;

public class TaskMemento {
    private final String state;

    public TaskMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

package Model;

public class TaskWithPriotity extends Task{
    private Priority priority;

    public TaskWithPriotity(String name, Priority priority) {
        super(name);
        this.priority = priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Priority getPriority() {
        return priority;
    }
}

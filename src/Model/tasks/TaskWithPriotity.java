package Model;

public class TaskWithPriotity extends Task{
    private Priority priority;

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Priority getPriority() {
        return priority;
    }
}

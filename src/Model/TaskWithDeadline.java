package Model;

import java.time.LocalDate;

public class TaskWithDeadline extends Task{
    private LocalDate deadline;
    public TaskWithDeadline(String name, LocalDate deadline) {
        super(name);
        setDeadline(deadline);
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if(!deadline.isAfter(LocalDate.now())){
            System.out.println("The deadline should be a future date. Please insert correct date.");
        }
        this.deadline = deadline;
    }
}


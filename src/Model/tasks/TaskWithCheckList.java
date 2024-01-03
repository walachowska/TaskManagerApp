package Model.tasks;

import Controller.mediator.Mediator;
import Model.memento.CheckListTaskMemento;
import Model.memento.DeadlineTaskMemento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskWithCheckList extends Task{
    private List<String> checkList;
    private Mediator mediator;
    public TaskWithCheckList() {
        this.checkList = new ArrayList<>();
    }
    public void setMediator(Mediator mediator){
        this.mediator=mediator;
    }

    public void markAsCompleted() {
        if (mediator != null) {
            this.setDateOfCompletion(LocalDate.now());
            mediator.logEvent("Task: " + this.getName() +" with " + checkList.size() + " subtasks" +
                    " completed on " + this.getDateOfCompletion());
        }
    }

    public void addSubTask(String subTask){
        checkList.add(subTask);
    }
    public void removeSubtask(String subTask){
        checkList.remove(subTask);
    }

    public CheckListTaskMemento saveMemento() {
        return new CheckListTaskMemento(getName(), getDescription(), getDateOfCompletion(), checkList);
    }

    public void restoreFromMemento(CheckListTaskMemento memento) {
        this.setName(memento.getName());
        this.setDescription(memento.getDescription());
        this.setDateOfCompletion(memento.getDateOfCompletion());
        this.checkList = memento.getCheckList();

    }
}

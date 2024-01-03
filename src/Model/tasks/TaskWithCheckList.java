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
    private CheckListTaskMemento memento;
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

    public void setCheckList(ArrayList<String> subtasks){
        this.checkList= subtasks;
    }
    public void saveMemento() {
        memento = new CheckListTaskMemento(getName(), getDescription(), getDateOfCompletion(), checkList);
    }

    public void restoreFromMemento() {
        this.setName(memento.getName());
        this.setDescription(memento.getDescription());
        this.setDateOfCompletion(memento.getDateOfCompletion());
        this.checkList = memento.getCheckList();

    }
}

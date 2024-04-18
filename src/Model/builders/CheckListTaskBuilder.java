package Model.builders;

import Controller.mediator.Mediator;
import Model.tasks.TaskWithCheckList;

import java.util.ArrayList;

public class CheckListTaskBuilder implements Builder{
    private TaskWithCheckList task;
    private static final String checklistHashtag = "task with checklist";

    @Override
    public CheckListTaskBuilder setName(String name) {
        this.task.setName(name);
        return this;
    }

    @Override
    public CheckListTaskBuilder setDescription(String description) {
        this.task.setDescription(checklistHashtag + "\n" + description);
        return this;
    }

    @Override
    public CheckListTaskBuilder setMediator(Mediator mediator) {
        this.task.setMediator(mediator);
        return this;
    }

    public CheckListTaskBuilder setChecklist(ArrayList<String> subtasks){
        this.task.setCheckList(subtasks);
        return this;
    }

    public CheckListTaskBuilder newTask() {
        this.task = new TaskWithCheckList();
        return this;
    }

    public TaskWithCheckList getResult(){
        this.task.saveMemento();
        return this.task;
    }
}

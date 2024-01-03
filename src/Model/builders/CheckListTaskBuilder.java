package Model.builders;

import Model.tasks.TaskWithCheckList;
import java.time.LocalDate;
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

    public CheckListTaskBuilder setChecklist(ArrayList<String> subtasks){
        this.task.setCheckList(subtasks);
        return this;
    }

    public CheckListTaskBuilder resetTask() {
        this.task = new TaskWithCheckList();
        return this;
    }

    public TaskWithCheckList getResult(){
        return this.task;
    }
}

package builders;

import Model.TaskWithCheckList;
import java.time.LocalDate;

public class CheckListTaskBuilder implements Builder{
    private TaskWithCheckList task;
    private static final String checklistHashtag = "task with checklist";

    public CheckListTaskBuilder(){
        this.task = new TaskWithCheckList();
    }
    @Override
    public Builder setName(String name) {
        this.task.setName(name);
        return this;
    }

    @Override
    public Builder setDescription(String description) {
        this.task.setDescription(checklistHashtag + "\n" + description);
        return this;
    }

    @Override
    public Builder setDateOfCompletion(LocalDate dateOfCompletion) {
        this.task.setDateOfCompletion(dateOfCompletion);
        return this;
    }

    public Builder addSubtask(String subTask){
        this.task.addSubTask(subTask);
        return this;
    }

    public Builder removeSubtask(String subTask){
        this.task.removeSubtask(subTask);
        return this;
    }

    @Override
    public void resetTask() {
        this.task = new TaskWithCheckList();
    }

    public TaskWithCheckList getResult(){
        return this.task;
    }
}

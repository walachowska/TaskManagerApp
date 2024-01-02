package Model;

import java.util.ArrayList;
import java.util.List;

public class TaskWithCheckList extends Task{
    private List<String> checkList;
    public TaskWithCheckList() {
        this.checkList = new ArrayList<>();
    }

    public void addSubTask(String subTask){
        checkList.add(subTask);
    }
    public void removeSubtask(String subTask){
        checkList.remove(subTask);
    }
}

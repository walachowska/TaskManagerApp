package Model.memento;

import java.time.LocalDate;
import java.util.List;

public class CheckListTaskMemento extends TaskMemento {
    private final List<String> checkList;
    public CheckListTaskMemento(String name, String description, LocalDate dateOfCompletion, List<String> checkList) {
        super(name, description, dateOfCompletion);
        this.checkList = checkList;
    }

    public List<String> getCheckList(){
        return checkList;
    }


}

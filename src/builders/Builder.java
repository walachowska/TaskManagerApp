package builders;

import java.time.LocalDate;

public interface Builder {
    Builder setName(String name);
    Builder setDescription(String description);
    Builder setDateOfCompletion(LocalDate dateOfCompletion);
    void resetTask();
}

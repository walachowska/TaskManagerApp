package Model.builders;

import Controller.mediator.Mediator;

import java.time.LocalDate;

public interface Builder {
    Builder setName(String name);
    Builder setDescription(String description);
    Builder setMediator(Mediator mediator);
}

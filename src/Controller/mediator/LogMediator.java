package Controller.mediator;


import Controller.Logger;

public class TaskMediator implements Mediator{
    private Logger logger;

    public TaskMediator(Logger logger){
        this.logger = logger;
    }
    @Override
    public void logEvent(String message) {
        logger.log(message);
    }
}

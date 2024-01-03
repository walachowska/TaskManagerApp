package Controller.mediator;


import Controller.Logger;

public class LogMediator implements Mediator{
    private Logger logger;

    public LogMediator(Logger logger){
        this.logger = logger;
    }
    @Override
    public void logEvent(String message) {
        logger.log(message);
    }
}

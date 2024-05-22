package Classes.Exceptions.QueueExceptions;

public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super("Queue vacio");
    }

}

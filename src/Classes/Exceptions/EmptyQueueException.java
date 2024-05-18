package Classes.Exceptions;

public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super("Queue vacio");
    }

}

package fr.peaceandcube.pacprofile.gui.exception;

public class NotPaginatedGuiException extends RuntimeException {

    public NotPaginatedGuiException() {
        super("Current user interface is not a paginated user interface");
    }
}

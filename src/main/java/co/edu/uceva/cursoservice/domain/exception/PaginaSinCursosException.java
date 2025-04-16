package co.edu.uceva.cursoservice.domain.exception;

public class PaginaSinCursosException extends RuntimeException {
    public PaginaSinCursosException(int page) {
        super("No hay cursos en la página solicitada: " + page);
    }
}
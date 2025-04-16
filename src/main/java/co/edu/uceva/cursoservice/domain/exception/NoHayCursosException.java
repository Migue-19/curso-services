package co.edu.uceva.cursoservice.domain.exception;

public class NoHayCursosException extends RuntimeException {
    public NoHayCursosException() {
        super("No hay cursos en la base de datos.");
    }
}
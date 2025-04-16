package co.edu.uceva.cursoservice.domain.exception;

public class CursoNoEncontradoException extends RuntimeException {
    public CursoNoEncontradoException(Long id) {
        super("El curso con ID " + id + " no fue encontrado.");
    }
}
package co.edu.uceva.cursoservice.domain.exception;

public class CursoExistenteException extends RuntimeException {
    public CursoExistenteException(String nombre) {
        super("El curso con nombre '" + nombre + "' ya existe.");
    }
}

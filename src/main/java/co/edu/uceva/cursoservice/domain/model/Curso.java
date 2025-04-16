package co.edu.uceva.cursoservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 2, max = 100, message = "El tamaño debe estar entre 2 y 100")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El número de créditos no puede ser nulo")
    @Min(value = 1, message = "El número de créditos debe ser al menos 1")
    private Integer creditos;

    @Min(value = 1, message = "La duración del curso debe ser al menos 1 semana")
    private Integer duracionSemanas;

    @Size(max = 100, message = "El nombre del archivo no puede exceder los 100 caracteres")
    @Pattern(
            regexp = "^[\\w,\\s-]+\\.(jpg|jpeg|png|gif|bmp|webp)$",
            message = "El nombre del archivo debe ser válido y tener una extensión permitida: jpg, jpeg, png, gif, bmp, webp"
    )
    private String imagen;
}

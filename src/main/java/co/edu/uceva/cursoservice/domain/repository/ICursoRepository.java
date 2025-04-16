package co.edu.uceva.cursoservice.domain.repository;

import co.edu.uceva.cursoservice.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface ICursoRepository extends JpaRepository<Curso, Long> {
}

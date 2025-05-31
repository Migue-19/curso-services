package co.edu.uceva.cursoservice.delivery.rest;

import co.edu.uceva.cursoservice.domain.exception.NoHayCursosException;
import co.edu.uceva.cursoservice.domain.exception.PaginaSinCursosException;
import co.edu.uceva.cursoservice.domain.exception.CursoNoEncontradoException;
import co.edu.uceva.cursoservice.domain.exception.ValidationException;
import co.edu.uceva.cursoservice.domain.model.Curso;
import co.edu.uceva.cursoservice.domain.service.ICursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/curso")
public class CursoRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final ICursoService cursoService;

    // Constantes para los mensajes de respuesta
    private static final String MENSAJE = "mensaje";
    private static final String CURSO = "curso";
    private static final String CURSOS = "cursos";

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public CursoRestController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    /**
     * Listar todos los productos.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/cursos")
    public ResponseEntity<Map<String, Object>> getCursos() {
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            throw new NoHayCursosException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(CURSOS, cursos);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar productos con paginación.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/curso/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Curso> cursos = cursoService.findAll(pageable);
        if (cursos.isEmpty()) {
            throw new PaginaSinCursosException(page);
        }
        return ResponseEntity.ok(cursos);
    }

    /**
     * Crear un nuevo producto pasando el objeto en el cuerpo de la petición, usando validaciones
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/cursos")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Curso nuevoCurso = cursoService.save(curso);
        response.put(MENSAJE, "El curso ha sido creado con éxito!");
        response.put(CURSO, nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Eliminar un producto pasando el objeto en el cuerpo de la petición.
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/cursos")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Curso curso) {
        cursoService.findById(curso.getId())
            .orElseThrow(() -> new CursoNoEncontradoException(curso.getId()));
        cursoService.delete(curso);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El curso ha sido eliminado con éxito!");
        response.put(CURSO, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un curso pasando el objeto en el cuerpo de la petición.
     * @param curso: Objeto Producto que se va a actualizar
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/cursos")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        cursoService.findById(curso.getId())
                .orElseThrow(() -> new CursoNoEncontradoException(curso.getId()));
        Map<String, Object> response = new HashMap<>();
        Curso cursoActualizado = cursoService.update(curso);
        response.put(MENSAJE, "El curso ha sido actualizado con éxito!");
        response.put(CURSO, cursoActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un producto por su ID.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id)
                .orElseThrow(() -> new CursoNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El curso ha sido encontrado con éxito!");
        response.put(CURSO, curso);
        return ResponseEntity.ok(response);
    }
}

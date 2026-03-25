package org.example.infrastructure.api;

import org.example.application.UseCases.ActualizarCitaUseCase;
import org.example.application.UseCases.AgendarCitaUseCase;
import org.example.application.UseCases.ConsultarCitasUseCase;
import org.example.application.UseCases.EliminarCitaUseCase;
import org.example.domain.exception.CitaNoEncontradaException;
import org.example.domain.model.Cita;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final AgendarCitaUseCase useCase;
    private final ConsultarCitasUseCase consultarCitasUseCase;
    private final ActualizarCitaUseCase actualizarCitaUseCase;
    private final EliminarCitaUseCase eliminarCitaUseCase;

    public CitaController(AgendarCitaUseCase useCase,
                          ConsultarCitasUseCase consultarCitasUseCase,
                          ActualizarCitaUseCase actualizarCitaUseCase,
                          EliminarCitaUseCase eliminarCitaUseCase) {
        this.consultarCitasUseCase = consultarCitasUseCase;
        this.useCase = useCase;
        this.actualizarCitaUseCase = actualizarCitaUseCase;
        this.eliminarCitaUseCase = eliminarCitaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cita agendarNuevaCita(@RequestBody Cita nuevaCita) {
        return useCase.ejecutar(nuevaCita);
    }

    @GetMapping
    public List<Cita> obtenerCitas() {
        return consultarCitasUseCase.ejecutar();
    }

    @PutMapping("/{id}")
    public Cita actualizarCita(@PathVariable UUID id, @RequestBody Cita cita) {
        return actualizarCitaUseCase.ejecutar(id, cita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable UUID id) {
        eliminarCitaUseCase.ejecutar(id);
        return ResponseEntity.ok("Cita con folio " + id + " eliminada exitosamente.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarExcepciones(RuntimeException ex) {
        return ResponseEntity.badRequest().body("No se pudo agendar: " + ex.getMessage());
    }

    @ExceptionHandler(CitaNoEncontradaException.class)
    public ResponseEntity<String> manejarNoEncontrado(CitaNoEncontradaException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}

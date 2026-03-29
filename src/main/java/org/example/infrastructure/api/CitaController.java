package org.example.infrastructure.api;

import org.example.application.UseCases.ActualizarCitaUseCase;
import org.example.application.UseCases.AgendarCitaUseCase;
import org.example.application.UseCases.ConsultarCitasUseCase;
import org.example.application.UseCases.EliminarCitaUseCase;
import org.example.application.dto.request.ActualizarCitaRequest;
import org.example.application.dto.request.CrearCitaRequest;
import org.example.application.dto.response.CitaResponse;
import org.example.domain.exception.CitaNoEncontradaException;
import org.example.domain.model.Cita;
import org.example.infrastructure.mapper.CitaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public CitaResponse agendarNuevaCita(@RequestBody CrearCitaRequest request) {
        Cita citaAgendada = useCase.ejecutar(request);

        return CitaMapper.toResponse(citaAgendada);
    }

    @GetMapping
    public List<CitaResponse> obtenerCitas() {
        List<Cita> citas = consultarCitasUseCase.ejecutar();
         return citas.stream()
                .map(CitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CitaResponse obtenerCitaPorId(@PathVariable UUID id) {
        Cita cita = useCase.obtenerPorId(id);
        if (cita == null) {
            throw new CitaNoEncontradaException("Cita con ID " + id + " no encontrada.");
        }
        return CitaMapper.toResponse(cita);
    }


    @PutMapping("/{id}")
    public CitaResponse actualizarCita(@PathVariable UUID id,
                                       @RequestBody ActualizarCitaRequest request) {

        Cita citaActualizada = actualizarCitaUseCase.ejecutar(id, request);
        return CitaMapper.toResponse(citaActualizada);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCita(@PathVariable UUID id) {
        eliminarCitaUseCase.ejecutar(id);
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

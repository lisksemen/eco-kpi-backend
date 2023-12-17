package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.eco.dto.EmergencyDto;
import ua.kpi.eco.dto.EmergencyResponseDto;
import ua.kpi.eco.service.EmergencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/emergencies")
public class EmergencyController {

    private final EmergencyService emergencyService;

    @PostMapping
    public ResponseEntity<EmergencyResponseDto> create(@RequestBody EmergencyDto emergencyDto) {
        var savedObject = emergencyService.create(emergencyDto);
        return new ResponseEntity<>(savedObject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyResponseDto> update(@PathVariable Long id, @RequestBody EmergencyDto emergencyDto) {
        var updatedObject = emergencyService.update(id,emergencyDto);
        return ResponseEntity.ok(updatedObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        emergencyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyResponseDto> get(@PathVariable Long id) {
        var retrievedObject = emergencyService.get(id);
        return ResponseEntity.ok(retrievedObject);
    }

    @GetMapping
    public ResponseEntity<List<EmergencyResponseDto>> getAll() {
        List<EmergencyResponseDto> list = emergencyService.getAll();
        return ResponseEntity.ok(list);
    }
}

package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.eco.dto.ObjectDto;
import ua.kpi.eco.dto.ObjectResponseDto;
import ua.kpi.eco.service.ObjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/objects")
public class ObjectController {

    private final ObjectService objectService;

    @PostMapping
    public ResponseEntity<ObjectResponseDto> create(@RequestBody ObjectDto objectDto) {
        var savedObject = objectService.create(objectDto);
        return new ResponseEntity<>(savedObject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponseDto> update(@PathVariable Long id, @RequestBody ObjectDto objectDto) {
        var updatedObject = objectService.update(id,objectDto);
        return ResponseEntity.ok(updatedObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        objectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponseDto> get(@PathVariable Long id) {
        var retrievedObject = objectService.get(id);
        return ResponseEntity.ok(retrievedObject);
    }

    @GetMapping
    public ResponseEntity<List<ObjectResponseDto>> getAll() {
        List<ObjectResponseDto> objects = objectService.getAll();
        return ResponseEntity.ok(objects);
    }
}

package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.dto.PollutionDto;
import ua.kpi.eco.dto.PollutionResponseDto;
import ua.kpi.eco.service.PollutionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/pollutions")
@RequiredArgsConstructor
public class PollutionController {

    private final PollutionService pollutionService;

    @GetMapping
    public List<AggregatedPollutionDto> getAll(){
        return pollutionService.getAll();
    }

    @GetMapping("/{id}")
    public PollutionResponseDto getPollution(@PathVariable Long id){
        return pollutionService.read(id);
    }

    @PutMapping("/{id}")
    public PollutionResponseDto updatePollution(@PathVariable Long id, @RequestBody PollutionDto pollutionDto){
        return pollutionService.update(id,pollutionDto);
    }

    @PostMapping
    public ResponseEntity<PollutionResponseDto> createPollution(@RequestBody PollutionDto pollutionDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pollutionService.create(pollutionDto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletePollution(@PathVariable Long id){
        pollutionService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}

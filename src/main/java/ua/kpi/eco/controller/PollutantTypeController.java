package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.eco.model.PollutantType;
import ua.kpi.eco.service.PollutantTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pollutantypes")
public class PollutantTypeController {

    private final PollutantTypeService pollutantTypeService;

    @GetMapping
    public List<PollutantType> getAll(){
        return pollutantTypeService.getAll();
    }

    @GetMapping("/{id}")
    public PollutantType getPollution(@PathVariable Long id){
        return pollutantTypeService.get(id);
    }
}

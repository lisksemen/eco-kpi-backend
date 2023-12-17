package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kpi.eco.exception.PollutantTypeNotFoundException;
import ua.kpi.eco.model.PollutantType;
import ua.kpi.eco.repository.PollutantTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutantTypeService {
    private final PollutantTypeRepository pollutantTypeRepository;

    public PollutantType get(Long id) {
        return pollutantTypeRepository.findById(id).orElseThrow(() -> new PollutantTypeNotFoundException("id = " + id));
    }

    public List<PollutantType> getAll() {
        return pollutantTypeRepository.findAll();
    }
}

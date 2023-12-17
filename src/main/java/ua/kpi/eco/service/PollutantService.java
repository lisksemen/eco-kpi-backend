package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.PollutantDto;
import ua.kpi.eco.dto.PollutantResponseDto;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.exception.PollutantTypeNotFoundException;
import ua.kpi.eco.mapper.PollutantMapper;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.model.PollutantType;
import ua.kpi.eco.repository.PollutantRepository;
import ua.kpi.eco.repository.PollutantTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutantService {
    private final PollutantRepository pollutantRepository;
    private final PollutantMapper pollutantMapper;
    private final PollutantTypeRepository pollutantTypeRepository;

    @Transactional
    public PollutantResponseDto create(PollutantDto pollutantDto) {
        PollutantType pollutantType = pollutantTypeRepository.findById(pollutantDto.pollutantTypeId()).orElseThrow(() -> new PollutantTypeNotFoundException("id = " + pollutantDto.pollutantTypeId()));
        var pollutantToCrete = pollutantMapper.pollutantDtoToPollutant(pollutantDto);
        pollutantToCrete.setPollutantType(pollutantType);
        var createdPollutant = pollutantRepository.save(pollutantToCrete);
        return pollutantMapper.pollutantToPollutantResponseDto(createdPollutant);
    }
    @Transactional
    public PollutantResponseDto update(Long id, PollutantDto pollutantDto) {
        Pollutant pollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        PollutantType pollutantType = pollutantTypeRepository.findById(pollutantDto.pollutantTypeId()).orElseThrow(() -> new PollutantTypeNotFoundException("id = " + id));
        pollutant.setName(pollutantDto.name());
        pollutant.setMfr(pollutantDto.mfr());
        pollutant.setElv(pollutantDto.elv());
        pollutant.setTlv(pollutantDto.tlv());
        pollutant.setSf(pollutantDto.sf());
        pollutant.setRfc(pollutantDto.rfc());
        pollutant.setPollutantType(pollutantType);
        pollutant.setTaxRate(pollutantDto.taxRate());
        return pollutantMapper.pollutantToPollutantResponseDto(pollutant);
    }
    @Transactional
    public void delete(Long id) {
        pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        pollutantRepository.deleteById(id);
    }

    public PollutantResponseDto get(Long id) {
        var retrievedPollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        return pollutantMapper.pollutantToPollutantResponseDto(retrievedPollutant);
    }

    public List<PollutantResponseDto> getAll() {
        return pollutantRepository.findAllBy(PollutantResponseDto.class);
    }
}

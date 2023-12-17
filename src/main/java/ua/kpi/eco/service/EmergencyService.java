package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.EmergencyDto;
import ua.kpi.eco.dto.EmergencyResponseDto;
import ua.kpi.eco.exception.EmeregencyNotFoundException;
import ua.kpi.eco.exception.ObjectNotFoundException;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.mapper.EmergencyMapper;
import ua.kpi.eco.model.Emergency;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.repository.EmergencyRepository;
import ua.kpi.eco.repository.ObjectRepository;
import ua.kpi.eco.repository.PollutantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    private final ObjectRepository objectRepository;
    private final PollutantRepository pollutantRepository;
    private final EmergencyMapper emergencyMapper;

    @Transactional
    public EmergencyResponseDto create(EmergencyDto emergencyDto) {
        var object = objectRepository.findByNameIgnoreCase(emergencyDto.objectName())
                .orElseThrow(() -> new ObjectNotFoundException("name = " + emergencyDto.objectName()));
        Pollutant pollutant = pollutantRepository.findById(emergencyDto.pollutantId())
                .orElseThrow(() -> new PollutantNotFoundException("id = " + emergencyDto.pollutantId()));

        var emergencyToCreate = Emergency.builder()
                .object(object)
                .pollutant(pollutant)
                .peopleCountDead(emergencyDto.peopleCountDead())
                .peopleCountFatalInjury(emergencyDto.peopleCountFatalInjury())
                .peopleCountLightInjury(emergencyDto.peopleCountLightInjury())
                .peopleCountStrongInjury(emergencyDto.peopleCountStrongInjury())
                .concentration(emergencyDto.concentration())
                .mass(emergencyDto.mass())
                .peopleLoss(Emergency.calcPeopleLoss(emergencyDto.peopleCountDead(), emergencyDto.peopleCountStrongInjury(),
                        emergencyDto.peopleCountLightInjury(), emergencyDto.peopleCountFatalInjury()))
                .emergencyLoss(Emergency.calcEmergencyLoss(emergencyDto.mass(),pollutant.getTaxRate(),
                        pollutant.getPollutantType().getId(),pollutant.getTlv(),emergencyDto.concentration()))
                .build();

        var createdObject = emergencyRepository.save(emergencyToCreate);
        return emergencyMapper.entityToEmergencyResponseDto(createdObject);
    }

    @Transactional
    public EmergencyResponseDto update(Long id, EmergencyDto emergencyDto) {
        var emergency = emergencyRepository.findById(id)
                .orElseThrow(() -> new EmeregencyNotFoundException("id = " + id));
        var object = objectRepository.findByNameIgnoreCase(emergencyDto.objectName())
                .orElseThrow(() -> new ObjectNotFoundException("name = " + emergencyDto.objectName()));
        var pollutant = pollutantRepository.findById(emergencyDto.pollutantId())
                .orElseThrow(() -> new PollutantNotFoundException("id = " + emergencyDto.pollutantId()));

        emergency.setObject(object);
        emergency.setPollutant(pollutant);
        emergency.setMass(emergencyDto.mass());
        emergency.setConcentration(emergencyDto.concentration());
        emergency.setPeopleCountDead(emergencyDto.peopleCountDead());
        emergency.setPeopleCountFatalInjury(emergencyDto.peopleCountFatalInjury());
        emergency.setPeopleCountLightInjury(emergencyDto.peopleCountLightInjury());
        emergency.setPeopleCountStrongInjury(emergencyDto.peopleCountStrongInjury());
        emergency.setPeopleLoss(Emergency.calcPeopleLoss(emergencyDto.peopleCountDead(), emergencyDto.peopleCountStrongInjury(),
                emergencyDto.peopleCountLightInjury(), emergencyDto.peopleCountFatalInjury()));
        emergency.setEmergencyLoss(Emergency.calcEmergencyLoss(emergencyDto.mass(),pollutant.getTaxRate(),
                pollutant.getPollutantType().getId(),pollutant.getTlv(),emergencyDto.concentration()));

        return emergencyMapper.entityToEmergencyResponseDto(emergency);
    }

    @Transactional
    public void delete(Long id) {
        emergencyRepository.findById(id).orElseThrow(() -> new EmeregencyNotFoundException("id = " + id));
        emergencyRepository.deleteById(id);
    }

    public EmergencyResponseDto get(Long id) {
        var retrievedObject = emergencyRepository.findById(id).orElseThrow(() -> new EmeregencyNotFoundException("id = " + id));
        return emergencyMapper.entityToEmergencyResponseDto(retrievedObject);
    }

    public List<EmergencyResponseDto> getAll() {
        return emergencyRepository.findAllBy(EmergencyResponseDto.class);
    }
}

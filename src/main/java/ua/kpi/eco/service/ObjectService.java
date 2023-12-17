package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.ObjectDto;
import ua.kpi.eco.dto.ObjectResponseDto;
import ua.kpi.eco.exception.ObjectNotFoundException;
import ua.kpi.eco.mapper.ObjectMapper;
import ua.kpi.eco.repository.ObjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public ObjectResponseDto create(ObjectDto objectDto) {

        var objectToCreate = objectMapper.objectDtoToObject(objectDto);

        var createdObject = objectRepository.save(objectToCreate);
        return objectMapper.objectToObjectResponseDto(createdObject);
    }

    @Transactional
    public ObjectResponseDto update(Long id, ObjectDto objectDto) {
        var object = objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));

        object.setName(objectDto.name());
        object.setDescription(objectDto.description());
        return objectMapper.objectToObjectResponseDto(object);
    }

    @Transactional
    public void delete(Long id) {
        objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));
        objectRepository.deleteById(id);
    }

    public ObjectResponseDto get(Long id) {
        var retrievedObject = objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));
        return objectMapper.objectToObjectResponseDto(retrievedObject);
    }

    public List<ObjectResponseDto> getAll() {
        return objectRepository.findAllBy(ObjectResponseDto.class);
    }
}

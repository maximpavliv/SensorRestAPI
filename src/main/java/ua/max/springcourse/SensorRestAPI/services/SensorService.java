package ua.max.springcourse.SensorRestAPI.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.springcourse.SensorRestAPI.dto.SensorDTO;
import ua.max.springcourse.SensorRestAPI.models.Sensor;
import ua.max.springcourse.SensorRestAPI.repositories.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public boolean sensorNameExists(String sensorName) {
        return sensorRepository.existsByName(sensorName);
    }

    @Transactional(readOnly = false)
    public void registerSensor(SensorDTO sensorDTO) {
        Sensor sensor = convertToEntity(sensorDTO);
        sensorRepository.save(sensor);
    }

    private Sensor convertToEntity(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}

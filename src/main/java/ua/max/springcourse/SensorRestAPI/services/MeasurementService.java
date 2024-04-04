package ua.max.springcourse.SensorRestAPI.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.springcourse.SensorRestAPI.dto.MeasurementDTO;
import ua.max.springcourse.SensorRestAPI.models.Measurement;
import ua.max.springcourse.SensorRestAPI.models.Sensor;
import ua.max.springcourse.SensorRestAPI.repositories.MeasurementRepository;
import ua.max.springcourse.SensorRestAPI.repositories.SensorRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = false)
    public void addMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = convertToEntity(measurementDTO);
        measurement.setMeasurementTime(new Date());
        Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
        sensor.getMeasurements().add(measurement);
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public int getNbRainyDays() {
        return measurementRepository.countByRainingIsTrue();
    }

    private Measurement convertToEntity(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}

package ua.max.springcourse.SensorRestAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.max.springcourse.SensorRestAPI.dto.MeasurementDTO;
import ua.max.springcourse.SensorRestAPI.services.SensorService;

@Component
public class MeasurementDTOValidator implements Validator {

    @Autowired
    private SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        // Check if sensor exists in the database
        String sensorName = measurementDTO.getSensor().getName();
        if (!sensorService.sensorNameExists(sensorName)) {
            errors.rejectValue("sensor.name", "measurement.sensor.notfound", "Sensor with this name does not exist");
        }
    }
}

package ua.max.springcourse.SensorRestAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.max.springcourse.SensorRestAPI.dto.SensorDTO;
import ua.max.springcourse.SensorRestAPI.services.SensorService;

@Component
public class SensorDTOValidator implements Validator {

    @Autowired
    private SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        String name = sensorDTO.getName();

        if (name == null || name.trim().isEmpty()) {
            errors.rejectValue("name", "sensor.name.empty", "Sensor name cannot be empty");
        }

        // Check if sensor with the same name already exists in the database
        if (sensorService.sensorNameExists(name)) {
            errors.rejectValue("name", "sensor.name.exists", "Sensor with this name already exists");
        }
    }
}

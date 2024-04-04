package ua.max.springcourse.SensorRestAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.max.springcourse.SensorRestAPI.dto.SensorDTO;
import ua.max.springcourse.SensorRestAPI.services.SensorService;
import ua.max.springcourse.SensorRestAPI.util.SensorDTOValidator;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorDTOValidator sensorDTOValidator;

    @PostMapping("/registration")
    public ResponseEntity<?> registerSensor(@Validated @RequestBody SensorDTO sensorDTO, BindingResult result) {
        sensorDTOValidator.validate(sensorDTO, result);
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        sensorService.registerSensor(sensorDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }
/*
{
    "name": "mySensor"
}
*/
}

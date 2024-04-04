package ua.max.springcourse.SensorRestAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.max.springcourse.SensorRestAPI.dto.MeasurementDTO;
import ua.max.springcourse.SensorRestAPI.services.MeasurementService;
import ua.max.springcourse.SensorRestAPI.util.MeasurementDTOValidator;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private MeasurementDTOValidator measurementDTOValidator;

    @PostMapping("/add")
    public ResponseEntity<?> addMeasurement(@Validated @RequestBody MeasurementDTO measurementDTO, BindingResult result) {
        measurementDTOValidator.validate(measurementDTO, result);
        if (result.hasErrors()) {
            System.out.println("Sensor Not found"); // todo delete
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        measurementService.addMeasurement(measurementDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("")
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public int getNbRainyDays() {
        return measurementService.getNbRainyDays();
    }
/*
{
    "value": 24.7,
    "raining": false,
    "sensor": {
        "name": "Hello"
    }
}
*/
}

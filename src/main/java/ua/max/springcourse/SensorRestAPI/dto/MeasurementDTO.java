package ua.max.springcourse.SensorRestAPI.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "-100", message = "Value must be greater than or equal to -100")
    @DecimalMax(value = "100", message = "Value must be less than or equal to 100")
    private Float value;

    @NotNull(message = "Raining cannot be null")
    private Boolean raining;

    @Valid
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Float value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensorDTO=" + sensor +
                '}';
    }
}

package ua.max.springcourse.SensorRestAPI.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value="-100", message = "Temperature must be greater than or equal to -100")
    @DecimalMax(value="100", message = "Temperature must be less than or equal to 100")
    @Column(nullable = false)
    private Float value;

    @NotNull(message = "Raining cannot be null")
    @Column(nullable = false)
    private Boolean raining;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "measurement_time", nullable = false)
    private Date measurementTime;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", nullable = false)
    private Sensor sensor;

    public Measurement() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "Measurement{" +
                "id=" + id +
                ", temperature=" + value +
                ", raining=" + raining +
                ", measurementTime=" + dateFormat.format(measurementTime) +
                ", sensor=" + sensor +
                '}';
    }
}

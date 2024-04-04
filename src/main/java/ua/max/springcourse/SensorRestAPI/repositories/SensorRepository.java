package ua.max.springcourse.SensorRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.springcourse.SensorRestAPI.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    boolean existsByName(String name);
    Sensor findByName(String name);
}

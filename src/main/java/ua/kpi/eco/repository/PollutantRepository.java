package ua.kpi.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.model.Pollutant;

import java.util.List;
import java.util.Optional;

public interface PollutantRepository extends JpaRepository<Pollutant, Long> {

    Optional<Pollutant> findByNameIgnoreCase(String name);

    <T> List<T> findAllBy(Class<T> type);
}
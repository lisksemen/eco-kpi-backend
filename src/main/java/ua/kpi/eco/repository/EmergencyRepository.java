package ua.kpi.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.model.Emergency;

import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    <T> List<T> findAllBy(Class<T> type);
}

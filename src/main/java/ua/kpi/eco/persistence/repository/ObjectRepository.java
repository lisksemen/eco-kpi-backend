package ua.kpi.eco.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.persistence.model.Object;

import java.util.List;
import java.util.Optional;

public interface ObjectRepository extends JpaRepository<Object, Long> {
    Optional<Object> findByNameIgnoreCase(String name);

    <T> List<T> findAllBy(Class<T> type);
}
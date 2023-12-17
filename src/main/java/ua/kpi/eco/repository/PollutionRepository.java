package ua.kpi.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.model.Pollution;

import java.util.List;
import java.util.Optional;

public interface PollutionRepository extends JpaRepository<Pollution, Long> {

    <T> List<T> findAllBy(Class<T> type);
    <T> List<T> findAllByOrderById(Class<T> type);
    <T> Optional<T> findPollutionById(long id, Class<T> type);
    Optional<Pollution> findByObjectIdAndYear(Long objectId, int year);
}
package ua.kpi.eco.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.persistence.model.PollutantType;

public interface PollutantTypeRepository extends JpaRepository<PollutantType,Long> {
}

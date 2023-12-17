package ua.kpi.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.model.PollutantType;

public interface PollutantTypeRepository extends JpaRepository<PollutantType,Long> {
}

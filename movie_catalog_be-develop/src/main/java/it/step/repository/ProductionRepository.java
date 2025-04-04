package it.step.repository;

import it.step.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Integer> {
}

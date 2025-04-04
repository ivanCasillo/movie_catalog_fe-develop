package it.step.repository;

import it.step.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Integer> {
}

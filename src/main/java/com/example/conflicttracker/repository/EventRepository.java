package com.example.conflicttracker.repository;

import com.example.conflicttracker.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Repositorio básico para Event
}

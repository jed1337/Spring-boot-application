package com.pluralsight.conference.repositories;

import com.pluralsight.conference.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

//Session is our data type, the primary key is Long
public interface SessionRepository extends JpaRepository<Session, Long> {
}

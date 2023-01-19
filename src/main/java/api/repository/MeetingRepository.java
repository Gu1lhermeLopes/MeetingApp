package api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import api.model.*;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByOrderByDateAsc();

    List<Meeting> findAllByDateBetween(LocalDateTime start,LocalDateTime end);
}

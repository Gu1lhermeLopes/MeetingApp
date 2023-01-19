package api.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import api.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}

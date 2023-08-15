package uz.pdp.springvalidationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springvalidationdemo.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}

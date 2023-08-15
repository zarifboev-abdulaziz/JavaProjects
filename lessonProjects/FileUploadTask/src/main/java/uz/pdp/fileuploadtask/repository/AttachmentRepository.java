package uz.pdp.fileuploadtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.fileuploadtask.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}

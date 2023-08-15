package uz.pdp.fileuploadanddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.fileuploadanddownload.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}

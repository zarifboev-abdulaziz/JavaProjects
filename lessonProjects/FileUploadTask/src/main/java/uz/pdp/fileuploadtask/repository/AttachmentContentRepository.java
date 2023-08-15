package uz.pdp.fileuploadtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.fileuploadtask.model.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);

}

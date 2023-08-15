package uz.pdp.springvalidationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springvalidationdemo.model.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
}

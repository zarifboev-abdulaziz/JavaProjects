package uz.pdp.generalmotorsdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.GMFactory;

@Repository
public interface GMFactoryRepository extends JpaRepository<GMFactory, Integer> {
}

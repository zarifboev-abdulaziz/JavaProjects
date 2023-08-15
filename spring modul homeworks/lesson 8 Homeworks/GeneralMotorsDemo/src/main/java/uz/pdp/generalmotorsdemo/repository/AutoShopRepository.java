package uz.pdp.generalmotorsdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.generalmotorsdemo.model.AutoShop;

import java.util.List;

@Repository
public interface AutoShopRepository extends JpaRepository<AutoShop, Integer> {

    @Query("select a from AutoShop a where a.gmFactory.id =:gmFactoryId")
    List<AutoShop> getAllAutoShopByGMFactory(Integer gmFactoryId);

}

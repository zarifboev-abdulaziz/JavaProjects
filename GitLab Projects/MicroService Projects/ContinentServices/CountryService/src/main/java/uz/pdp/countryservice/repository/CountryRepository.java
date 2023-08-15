package uz.pdp.countryservice.repository;

import com.sun.org.apache.bcel.internal.generic.INEG;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.countryservice.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}

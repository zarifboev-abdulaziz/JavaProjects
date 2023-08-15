package uz.pdp.springsecuritydemocrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemocrud.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {


}

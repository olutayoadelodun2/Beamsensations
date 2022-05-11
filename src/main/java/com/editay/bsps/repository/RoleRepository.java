package com.editay.bsps.repository;



import com.editay.bsps.models.ERole;
import com.editay.bsps.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(ERole name);
}

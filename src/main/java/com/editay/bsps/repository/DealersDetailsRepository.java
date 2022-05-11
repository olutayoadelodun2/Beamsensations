package com.editay.bsps.repository;

import com.editay.bsps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealersDetailsRepository extends JpaRepository<User, String> {
}

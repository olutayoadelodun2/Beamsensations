package com.editay.bsps.repository;

import com.editay.bsps.dto.UserDto;
import com.editay.bsps.models.Role;
import com.editay.bsps.models.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public static final String FIND_USER2 = "SELECT u.id,u.address,u.city,u.email,u.firstname,u.middlename,u.surname,u.mobile,u.phonenumber,u.state,u.title,"
			+ "u.username,u.zipcode,u.password, us.role_id FROM users u inner join user_roles us on u.id = us.user_id where u.username = :username";

	public static final String FIND_USER = "select new com.editay.bsps.dto(u.firstname,u.middlename,u.surname,u.phonenumber,u.mobile,u.address,u.city,u.state,u.zipcode,u.username,u.title,"
			+ "u.email,r.roles) FROM User u INNER JOIN u.roles r where u.username= ?1 ";

	Optional<User> findByUsername(String username);

	@Query(value = FIND_USER2, nativeQuery = true)
	Optional<User> findByUsername3(String username);

	@Query(value = FIND_USER, nativeQuery = true)
	List<UserDto> findByUsername2(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	List<User> findBySurnameContaining(String surname, Pageable pageable);

	List<User> findBySurnameContaining(String surname, Sort sort);

	List<User> findByPhonenumber(String phone);

	Page<User> findBySurname(boolean surname, Pageable pageable);

	@Query(value = "select * from #{#entityName} e where e.username=?1", nativeQuery = true)
	User getByUsername(String username);

	@Query(value = "select * from #{#entityName} e where e.id=?1", nativeQuery = true)
	User getById(long id);

	List<User> findByEmail(String email);
}

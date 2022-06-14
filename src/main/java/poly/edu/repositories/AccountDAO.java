package poly.edu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.models.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
	
	List<Account> findByAdminEqualsAndActivateEquals(boolean isAdmin, boolean isActive);

	Optional<Account> findByUsernameEqualsAndAdminEquals(String username, boolean isAdmin);
	
	Page<Account> findByAdminEquals(boolean admin, Pageable pageable);
}

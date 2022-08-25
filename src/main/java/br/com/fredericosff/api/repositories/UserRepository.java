package br.com.fredericosff.api.repositories;

import br.com.fredericosff.api.domain.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

  Optional<Users> findByEmail(String email);
}

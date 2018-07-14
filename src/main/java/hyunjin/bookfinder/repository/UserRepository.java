package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}


package ang.neggaw.graphqls.repositories;

import ang.neggaw.graphqls.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * author by: ANG
 * since: 29/08/2021 13:16
 */

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByIdUser(long idUser);
    UserApp findByUsername(String username);
}

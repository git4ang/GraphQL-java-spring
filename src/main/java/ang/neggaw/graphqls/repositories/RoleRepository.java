package ang.neggaw.graphqls.repositories;

import ang.neggaw.graphqls.entities.RoleApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * author by: ANG
 * since: 29/08/2021 13:16
 */

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<RoleApp, Long> {
    RoleApp findByIdRole(long idRole);
    RoleApp findByRoleName(String roleName);
}

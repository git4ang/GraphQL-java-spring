package ang.neggaw.graphqls.services;

import ang.neggaw.graphqls.entities.RoleApp;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 13:27
 */

public interface RoleService {
    RoleApp createRole(RoleApp role);
    RoleApp getRole(long idRole);
    Collection<RoleApp> allRoles(int offset, int count);
    Object updateRole(long idRole, RoleApp role);
    Object removeRoleFromUser(long idRole, long idUser);
    Object deleteRole(long idRole);
}

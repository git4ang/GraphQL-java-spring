package ang.neggaw.graphqls.graphqls.mutations;

import ang.neggaw.graphqls.entities.RoleApp;
import ang.neggaw.graphqls.services.RoleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * author by: ANG
 * since: 29/08/2021 13:33
 */

@Log4j2
@RequiredArgsConstructor
@Component
public class RoleMutation implements GraphQLMutationResolver {

    private final RoleService roleService;

    public RoleApp createRole(final String roleName) {

        log.info("Creating Role with roleName: '{}'...", roleName);

        RoleApp role = RoleApp.builder().roleName(roleName).build();
        role.setUsers(null);
        role = roleService.createRole(role);

        log.info("Role with id: '{}' CREATED successfully", role.getIdRole());
        return role;
    }

    public RoleApp updateRole(final long idRole, final RoleApp role) {

        log.info("Updating Role with id: '{}'...", idRole);
        Object resp = roleService.updateRole(idRole, role);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return null;
        }

        log.info("Role with id: '{}' UPDATED successfully", idRole);
        return (RoleApp) resp;
    }

    public String removeRoleFromUser(final long idRole, final long idUser) {

        log.info("Removing Role with id: '{}' from User with id: '{}'...", idRole, idUser);

        Object resp = roleService.removeRoleFromUser(idRole, idUser);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("Role with id: '{}' REMOVED successfully from User with id: '{}'", idRole, idUser);
        return String.format("Role with id: '%d' REMOVED successfully from User with id: '%d'", idRole, idUser);
    }

    public String deleteRole(final long idRole) {

        log.info("Deleting Role with id: '{}'...", idRole);
        Object resp = roleService.deleteRole(idRole);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("Role with id: '{}' DELETED successfully", idRole);
        return String.format("Role with id: '%s' DELETED successfully", idRole);
    }
}

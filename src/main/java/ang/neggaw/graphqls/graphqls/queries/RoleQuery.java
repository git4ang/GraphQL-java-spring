package ang.neggaw.graphqls.graphqls.queries;

import ang.neggaw.graphqls.entities.RoleApp;
import ang.neggaw.graphqls.services.RoleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 13:33
 */

@Log4j2
@RequiredArgsConstructor
@Component
public class RoleQuery implements GraphQLQueryResolver {

    private final RoleService roleService;

    public RoleApp getRole(final long idRole) {
        RoleApp role = roleService.getRole(idRole);
        if(role == null) return RoleApp.builder().idRole(0L).roleName("null").build();
        return role;
    }

    public Collection<RoleApp> allRoles(final int offset, final int count) {
        return roleService.allRoles(offset, count);
    }
}


package ang.neggaw.graphqls.services.implementations;

import ang.neggaw.graphqls.entities.RoleApp;
import ang.neggaw.graphqls.entities.UserApp;
import ang.neggaw.graphqls.repositories.RoleRepository;
import ang.neggaw.graphqls.repositories.UserRepository;
import ang.neggaw.graphqls.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 13:27
 */

@Log4j2
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public RoleApp createRole(RoleApp role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleApp getRole(long idRole) {
        return roleRepository.findByIdRole(idRole);
    }

    @Override
    public Collection<RoleApp> allRoles(int offset, int count) {
        offset = offset > roleRepository.findAll().size() ? 0: offset;
        count = Math.min(count, roleRepository.findAll().size());
        return roleRepository.findAll().subList(offset, count);
    }

    @Override
    public Object updateRole(long idRole, RoleApp role) {
        RoleApp roleDB = getRole(idRole);
        if(roleDB == null) return String.format("Unable to update role. Role with id: '%s' Not Found.", idRole);

        role.setUsers(role.getUsers());
        role.setIdRole(idRole);
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Object removeRoleFromUser(long idRole, long idUser) {
        RoleApp role = getRole(idRole);
        UserApp user = userRepository.findByIdUser(idUser);

        if(role == null) return String.format("Unable to Remove role. Role with id: '%s' Not Found.", idRole);
        else if(user == null) return String.format("Unable to Remove role. User with id: '%s' Not Found.", idUser);

        if(user.getRoles().contains(role)) {
            role.getUsers().remove(user);
            user.getRoles().remove(role);
        }

        return role;
    }

    @Override
    public Object deleteRole(long idRole) {
        RoleApp role = getRole(idRole);
        if(role == null) return String.format("Unable to Delete role. Role with id: '%s' Not Found.", idRole);
        roleRepository.delete(role);
        return role;
    }
}
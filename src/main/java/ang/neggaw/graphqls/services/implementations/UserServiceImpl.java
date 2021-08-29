package ang.neggaw.graphqls.services.implementations;

import ang.neggaw.graphqls.entities.RoleApp;
import ang.neggaw.graphqls.entities.UserApp;
import ang.neggaw.graphqls.repositories.RoleRepository;
import ang.neggaw.graphqls.repositories.UserRepository;
import ang.neggaw.graphqls.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * author by: ANG
 * since: 29/08/2021 13:27
 */

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Object createUser(UserApp user) {
        RoleApp role = roleRepository.findByIdRole(user.getIdRole());
        if(role == null) return String.format("Unable to create User. Role with id: '%d' Not Found", user.getIdRole());
        user.setRoles(Collections.singleton(role));
        return userRepository.save(user);
    }

    @Override
    public UserApp getUser(long idUser) {
        return userRepository.findByIdUser(idUser);
    }

    @Override
    public Collection<UserApp> allUsers(int offset, int count) {
        offset = offset > userRepository.findAll().size() ? 0 : offset;
        count = Math.min(count, userRepository.findAll().size());
        return userRepository.findAll().subList(offset, count);
    }

    @Override
    public Object updateUser(long idUser, UserApp user) {
        UserApp u = getUser(idUser);
        if(u == null) return String.format("Unable to update user. User with id: '%d' Not Found", idUser);

        user.setRoles(u.getRoles());
        user.setIdUser(idUser);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Object addUserToRole(long idUser, long idRole) {
        UserApp user = getUser(idUser);
        RoleApp role = roleRepository.findByIdRole(idRole);

        if(user == null) return String.format("Unable to add user. User with id: '%d' Not Found", idUser);
        else if(role == null) return String.format("Unable to add user. Role with id: '%d' Not Found", idRole);

        if(user.getRoles().contains(role)) return String.format("Role with id: '%d' is already added to User with id '%d'", idRole, idUser);
        else {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        return user;
    }

    @Override
    public Object deleteUser(long idUser) {
        UserApp u = getUser(idUser);
        if(u == null) return String.format("Unable to delete user. User with id: '%d' Not Found", idUser);
        userRepository.delete(u);
        return u;
    }
}
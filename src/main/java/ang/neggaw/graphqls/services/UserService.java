package ang.neggaw.graphqls.services;

import ang.neggaw.graphqls.entities.UserApp;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 13:27
 */

public interface UserService {
    Object createUser(UserApp user);
    UserApp getUser(long idUser);
    Collection<UserApp> allUsers(int offset, int count);
    Object updateUser(long idUser, UserApp user);
    Object addUserToRole(long idUser, long idRole);
    Object deleteUser(long idUser);
}

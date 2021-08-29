package ang.neggaw.graphqls.graphqls.mutations;

import ang.neggaw.graphqls.entities.UserApp;
import ang.neggaw.graphqls.services.UserService;
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
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    public UserApp createUser(UserApp user) {

        log.info("Creating User with username: '{}'...", user.getUsername());

        Object resp = userService.createUser(user);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return null;
        }

        UserApp u = (UserApp) resp;
        log.info("User with id: '{}' CREATED successfully", u.getIdUser());
        return u;
    }

    public UserApp updateUser(final long idUser, UserApp user) {

        log.info("Updating User with id: '{}'...", idUser);
        Object resp = userService.updateUser(idUser, user);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return null;
        }

        UserApp u = (UserApp) resp;
        log.info("User with id: '{}' UPDATED successfully", u.getIdUser());
        return u;
    }

    public String addUserToRole(final long idUser, final long idRole) {

        log.info("Adding User with id: '{}' to Role with '{}'...", idUser, idRole);

        Object resp = userService.addUserToRole(idUser, idRole);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("User with id: '{}' ADDED successfully to Role with '{}'", idUser, idRole);
        return String.format("User with id: '%d' ADDED successfully to Role with '%d'", idUser, idRole);
    }

    public String deleteUser(final long idUser) {

        log.info("Deleting User with id: '{}'...", idUser);
        Object resp = userService.deleteUser(idUser);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("User with id: '{}' DELETED successfully.", idUser);
        return String.format("User with id: '%s' DELETED successfully.", idUser);
    }
}
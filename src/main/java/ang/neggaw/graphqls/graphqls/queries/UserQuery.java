package ang.neggaw.graphqls.graphqls.queries;

import ang.neggaw.graphqls.entities.UserApp;
import ang.neggaw.graphqls.services.UserService;
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
public class UserQuery implements GraphQLQueryResolver {

    private final UserService userService;

    public UserApp getUser(final long idUser) {
        UserApp user = userService.getUser(idUser);
        if(user == null) UserApp.builder().idUser(0L).username(null).email(null).roles(null).build();
        return userService.getUser(idUser);
    }

    public Collection<UserApp> allUsers(final int offset, final int count) {
        return userService.allUsers(offset, count);
    }
}

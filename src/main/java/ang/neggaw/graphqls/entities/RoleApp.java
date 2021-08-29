package ang.neggaw.graphqls.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 13:15
 */

@Entity
@Table(name = "roles")
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class RoleApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "roles" })
    private Collection<UserApp> users = new ArrayList<>();;
}

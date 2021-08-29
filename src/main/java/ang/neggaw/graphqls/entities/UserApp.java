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
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class UserApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private TypeEnabled typeEnabled;

    @Transient
    private long idRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_with_role",
            joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    )
    @JsonIgnoreProperties(value = { "users" })
    private Collection<RoleApp> roles = new ArrayList<>();

    public enum TypeEnabled { ACTIVE, INACTIVE, BANNED, SUSPEND }
}
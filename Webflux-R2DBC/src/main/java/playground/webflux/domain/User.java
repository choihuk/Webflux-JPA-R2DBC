package playground.webflux.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Table("USER")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User {

    @Id
    private Long id;

    private String username;

//    @PersistenceCreator
//    public User(Long id, String username) {
//        this.id = id;
//        this.username = username;
//    }

    public User(String username) {
        this.username = username;
    }
}

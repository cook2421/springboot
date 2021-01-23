package springboot.config.auth.dto;

import lombok.Getter;
import springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}



/*

왜, User 클래스를 쓰지 않고 새로 만들어서 쓰는가?
* User 클래스가 엔티티이기 때문이. 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모른다.
  예를 들어, @OneToMany, @ManyToMany 등 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지
  포함되니 성능 이슈, 부수 효과가 발생할 확률이 높다. 그래서 직렬화 기능을 가진 세션 Dto를 하나
  추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.

*/
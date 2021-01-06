package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
// Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.
// Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.
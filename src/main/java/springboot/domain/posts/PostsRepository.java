package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();

}
// Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.
// Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.



/*

※ 1
규모가 있는 프로젝트에서의 데이터 조회는 PK의 조인, 복잡한 조건 등으로 인해
이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
대표적 예로 querydsl, jooq, MyBatis 등이 있다.
조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 사용한다.

Querydsl 추천 및 장점
* 타입 안정성이 보장된다.
    단순한 문자열로 쿼리를 생성하는 것이 아니라, 메소드 기반으로 쿼리를 생성하기 때문에
    오타나 존재하지 않는 컬럼명을 명시할 경우 IDE에서 자동으로 검출된다.
    이 장점은 Jooq에서도 지원하는 장점이지만, MyBatis에서는 지원하지 않는다.
* 국내 많은 회사에서 사용 중이다.
* 레퍼런스가 많다.

*/
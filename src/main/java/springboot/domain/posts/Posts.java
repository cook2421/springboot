package springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor      // ..(5)
@Entity     // ..(1)
public class Posts {

    @Id     // ..(2)
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // ..(3)
    private Long id;

    @Column(length = 500, nullable = false)     // ..(4)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // ..(6)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}



/*

(1)
@Entity
* 테이블과 링크될 클래스임을 나타낸다.
* 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
* ex) SalesManager.java -> sales_manager table

(2)
@Id
* 해당 테이블의 PK필드를 나타낸다.

(3)
@GeneratedValue
* PK의 생성 규칙을 나타낸다.
* 스프링 부트 2.0에서는 GeneratedType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.

(4)



*/
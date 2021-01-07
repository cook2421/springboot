package springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass     //..(1)
@EntityListeners(AuditingEntityListener.class)    //..(2)
public class BaseTimeEntity {

    @CreatedDate      //..(3)
    private LocalDateTime createdDate;

    @LastModifiedDate   //..(4)
    private LocalDateTime modifiedDate;
}



/*

(1)
@MappedSuperclass
* JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createDate, modifiedDate)도
  컬럼으로 인식하도록 한다.

(2)
@EntityListeners
* BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.

(3)
@CreatedDate
* Entity가 생성되어 저장될 때 시간이 자동 저장된다.

(4)
@LastModifiedDate
* 조회한 Entity의 값을 변경할 때 시간이 자동으로 저장된다.

*/

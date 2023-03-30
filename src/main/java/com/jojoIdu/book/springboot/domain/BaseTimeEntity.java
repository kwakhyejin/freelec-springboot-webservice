package com.jojoIdu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {

//    모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할.

    @CreatedDate // Entity 생성시 createdDate 자동 저장됨.
    private LocalDateTime createdDate;

    @LastModifiedDate   // 조회한 Entity 값을 변경시 시간 자동 저장됨.
    private LocalDateTime modifiedDate;
}

package himedia.connectus.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
//MappedSuperclass: 다른 엔티티가 본 클래스를 상속받을 경우 본 클래스의 필드를 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)
//audit: Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
public abstract class PostTime {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}
package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 엔티티 선언, JPA 처음 로딩 시 JPA를 사용하는 클래스임을 인식
//@Table(name ="USER")    // 특정 DB 테이블에 등록
public class Member {
    
    @Id // PK임을 알림
    private Long id;
    private String name;
    
    public Member() {
    }
    
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

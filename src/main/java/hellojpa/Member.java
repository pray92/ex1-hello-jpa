///*
//package hellojpa;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity // 엔티티 선언, JPA 처음 로딩 시 JPA를 사용하는 클래스임을 인식
//// @Table(name ="USER")    // 특정 DB 테이블에 등록
//public class Member {
//
//    @Id // PK임을 알림
//    private Long id;
//
//    // @Column(unique = true, length = 10) // 제약조건 추가
//    private String name;
//    private int gogo;
//
//    public Member() {
//    }
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
//*/

package hellojpa;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {
//
//    @Id
//    private Long id;
//
//    // name : 필드와 매핑할 테이블의 Column 이름
//    // insertable, updatable : 등록, 변경 가능 여부
//    // nullable(DLL) : null 헝용 여부, false 시 DDL 생성 시 not null 제약조건 붙음
//    // unique(DLL) : @Table의 uniqueConstraints와 같지만 한 Column에 간단히 유니크 제약 걸 떄사용
//    // length : 문자 길이 제약조건, String only
//    // precision, scale(DDL) : BigDecimal 타입에서 사용
//    //                          precision은 소수점을 포함한 전체 자릿수, scale은 소수의 자릿수
//    //                          double, float 적용X, 아주 큰 숫자나 정밀한 소수를 다룰때만 사용
//    // columnDefinition : 직접 제약 조건을 넣음
//    @Column(name = "name")
//    private String username;
//
//    private int age;                    // 숫자 타입 등록
//
//    // ENUM 위치 변경 시 치명적인 버그가 발생하므로
//    // STRING을 해줘야 함
//    @Enumerated(EnumType.STRING)        // Java Enum Type 사용
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)   // 날짜 타입,
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)   // 날짜 타입
//    private Date lastModifiedDate;
//
//    // JDK 8로 넘어오면서 LocalDate, LocalDateTime 지원하므로 안써도 됨
//    // 최신 Hibernate 에서도 이를 지원
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob                                // varchar를 넘어선 큰 값
//                                        // 매핑 타입 문자 -> CLOB, 숫자 -> BLOB(Byte)
//    private String description;
//
//    @Transient // 특정 필드를 Column 에 매핑하지 않음(무시)
//    int notMapping;
//
//    public Member() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "MEMBER_SEQ_GENERATOR")
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;
//
//    public Member(){
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@ManyToOne						// Member 입장에서 다, Team 입장에선 1 -> 다대1
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void changeTeam(Team team) {
		this.team = team;
		// 양방향 연관관계 초기화 -> 연관관계 편의 메소드
		// 실제로 관례상 setter에서 쓰지 않음 -> change로 바꿔준다
		team.getMembers().add(this);
	}
	
	/**
	 * NOTE: Team에선 Member의 toString을 호출하고
	 * Member에선 Team의 toString을 호출하게 되어 무한 루프가 발생
	 * 따라서 양방향 매핑 시에 무한루프를 주의하자
	 * @return
	 */
//	@Override
//	public String toString() {
//		return "Member{" +
//				"id=" + id +
//				", username='" + username + '\'' +
//				", team=" + team +
//				'}';
//	}
	
	//	public Long getTeamId() {
//		return teamId;
//	}
//
//	public void changeTeamId(Long teamId) {
//		this.teamId = teamId;
//	}
}
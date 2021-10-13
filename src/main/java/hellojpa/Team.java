package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
	
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;
	
	// mappedBy
	// 객체와 테이블 간에 연관관계를 맺는 차이를 이해해야 함
	// 객체는 연관관계가 2개, 단방향이 2개
	// 회원 -> 팀 1개
	// 팀 -> 회원 1개
	// 테이블 연관관계는 1개, ID를 알고 있기만 해도 상호 연관관계가 구성됨
	// 객체의 경우 상호 참조가 되어있어야 함, 사실 양방향 관계가 아니라 서로 다른 단방향 2개다
	// 객체를 양방향으로 참조하려면 단방향 연관관계를 2개 만들어야 함
	// 테이블은 외래 키 하나로 두 테이블의 연관관계를 관리(양쪽 조인이 가능)
	// 예를 들면 특정 멤버의 팀을 변경하고 싶을 때 테이블의 경우 멤버 테이블에서 TEAM_ID만 변경해도 된다
	// 하지만 객체 입장에서는 양방향이 되면서 Member 객체와 Team 내 members도 변경해줘야 함
	// 그래서 둘 중 하나로 외래키를 관리해야 한다. 즉 주인(Owner)를 정해야 함
	// 연관관계의 주인만이 외래 키를 관리(등록, 수정)
	// 주인이 아닌 쪽은 읽기만 가능
	// 주인은 mappedBy 속성 사용X -> 따라서 Team은 Owner가 아니다.
	// 주인은 Member가 된다
	// 주인의 반대편은 김영한님 기준으로 '가짜 매핑'이라 칭함
	// 외래키가 있는 곳(Many 쪽)을 주인(Owner)으로 정해라
	@OneToMany(mappedBy = "team" /* 변수 명 */) 	/* 나는 team에 의해 관리된다. members는 조회용이다. */
	private List<Member> members = new ArrayList<>();
	// 일대다 단방향, 즉 일인 엔티티를 Owner로 두는 경우, @OneToMany(매핑X), @JoinColumn(name = "TEAM_ID")로 선언
	// 비추천하는 방식이지만 객체 지향 설계상 이럴 가능성이 꽤 있다고 한다.
	
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
	
	public List<Member> getMembers() {
		return members;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	/**
	 * NOTE: Team에선 Member의 toString을 호출하고
	 * Member에선 Team의 toString을 호출하게 되어 무한 루프가 발생
	 * 따라서 양방향 매핑 시에 무한루프를 주의하자
	 * @return
	 */
//	@Override
//	public String toString() {
//		return "Team{" +
//				"id=" + id +
//				", name='" + name + '\'' +
//				", members=" + members +
//				'}';
//	}
}

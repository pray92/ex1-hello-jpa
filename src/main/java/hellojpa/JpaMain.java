package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        // persistence-unit에 있는 것을 파라미터로
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        {
            EntityManager em = emf.createEntityManager();
            {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                try {
                    /*Member member = em.find(Member.class, 1L);      // 조회
                    // em.remove(member);                               // 삭제
                    // member.setName("HelloJPA");                     // 업데이트(persist 불필요)
                    // em.persist(member);
                    
                    // 전체 회원 검색
                    List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();
                    
                    // 5번째부터 8개까지 검색
                    List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .setFirstResult(5)
                            .setMaxResults(8)
                            .getResultList();
                    for(Member mem : result){
                        System.out.println("member.name = " + mem.getName());
                    }*/
    
                    // 비영속 -> 영속 컨텍스트에 없음
//                    Member member = new Member();
//                    member.setId(101L);
//                    member.setName("HelloJPA");
                    
                    // 영속, 객체를 엔티티 매니저에 저장 -> 엔티티 매니저를 통해 영속 컨텍스트에 등록
                    // 이때 DB에 저장 X, 트랜잭션을 지원하는 쓰기 지연(Delay)
                    // Persist를 수행하면 등록만하고 DB에 보내지 않음
//                    em.persist(member);
                    // 준영속, 회원 엔티티를 영속성 컨텍스트에서 분리
//                    em.detach(member);
                    // 객체를 삭제한 상태(삭제)
//                    em.remove(member);
    
//                    Member findMember1 = em.find(Member.class, 101L);
//                    Member findMember2 = em.find(Member.class, 101L);
//
//                    // 동일성 보장
//                    System.out.println(findMember1 == findMember2);
                    
                    // 트랜잭션을 지원하는 쓰기 지연
//                    Member member1 = new Member(150L, "A");
//                    Member member2 = new Member(160L, "B");
                    
                    // 영속성 컨텍스트에 쌓임 -> 버퍼링 기능 사용 가능
//                    em.persist(member1);
//                    em.persist(member2);
//
//                    System.out.println("==============");
                    
                    // 엔티티 수정 -> 변경 감지(Dirty Checking)
//                    Member member = em.find(Member.class, 150L);
//                    member.setName("ZZZZ");
                    // Persist 불필요, Collection 처럼 생각
                    // 엔티티 매니저에서 불러온 엔티티는 call-by-reference, 알아서 바뀜
                    // em.persist(member);
    
//                    Member member = new Member(200L, "member200");
//                    em.persist(member);
//
//                    em.flush(); // 강제 호출 -> 1차 캐시는 유지, 변경 내용을 DB에 동기화
//                    System.out.println("==============");
                    
                    // 준영속 상태로 만드는 방법
//                    Member member = em.find(Member.class, 150L);
//                    member.setName("AAAAA");
                    
                    // detach
                    // em.detach(member);
                    
                    // 영속성 컨텍스트를 완전히 초기화
                    // em.clear();
                    
                    // 영속성 컨텍스트를 종료
                    // em.close();
                    ;
                    // 가장 많이 하는 실수 1
                    // 관계의 Owner는 Member의 Team
                    // 정상적인 매핑이 일어나지 않는다 (TeamID null)
                    // Team의 members는 읽기 전용(OneToMany(mappedBy))
                    // 따라서 Owner를 통해 관계를 설정해야 함
                    // 저장
//                    Member member = new Member();
//                    member.setUsername("member1");
//                    // 영속 상태(persist)가 되면 Id가 자동 생성됨
//                    // member.setTeamId(team.getId());
//                    em.persist(member);
//
//                    Team team = new Team();
//                    team.setName("TeamA");
//                    team.getMembers().add(member);
//                    em.persist(team);
    
                    ;
                    // 가장 많이 하는 실수 2
                    // 양방향 매핑 시 연관관계의 주인에 값을 입력해야 함
                    // 그러나 순수 객체 관계를 고려하면 항상 양쪽 다 값을 입력해야 함
                    // 저장
//                    Team team = new Team();
//                    team.setName("TeamA");
//                    em.persist(team);
//
//                    Member member = new Member();
//                    member.setUsername("member1");
//                    member.changeTeam(team);
//                    em.persist(member);
//
////                    em.flush();
////                    em.clear();
//
//                    // 1차 캐시에 있는 것을 가져오는데
//                    // 이 때 member가 DB에 등록되어 있지 않으면(flush를 안함)
//                    // 1차 캐시에 있음에도 members 개수가 0개가 됨
//                    // 따라서 순수 객체 관계랑 로직을 위해서라도
//                    // 양방향 연관관계는 양쪽에 다 값을 세팅해줘야 한다.
//                    // 권장 방법
//                    // 연관관계 편의 메서드를 생성(setMember 참조)
//                    // 편의 메서드를 양쪽에 넣으면 무한루프 가능성이 있음, 한쪽에 넣는 걸 권장
//                    // 양방향 매핑 시 무한 루프를 조심(toString, lombok, JSON 생성 라이브러리)
//                    
//                    Team findTeam = em.find(Team.class, team.getId());
//                    List<Member> members = findTeam.getMembers();
                    ;
                    
                    // 연관관계 고려사항 3가지
                    // 1. 다중성
                    // 2. 단방향, 양방향
                    // 3. 연관관계 주인
                    
                    // Team의 members 일대다, Owner가 일인 경우(일반적으로 다가 Owner)
                    // 이 경우, 쿼리문도 더 나감(Insert * 2, Update * 1(추가)) -> 성능 이슈, 그리 크진 않음
                    // 수 십개의 테이블에서 쿼리문이 여러개 나가는 경우엔 로직 관리가 힘들어짐
                    // 그래서 되도록 다대일 단방향을 수행하고, 필요에 따라 양방향으로 가는 방식으로
                    // (다가 Owner, 일쪽은 Read-only 식으로)
                    //
                    // 추가로, 일대다 단방향은 일대다에서 일이 연관관계 주인인데
                    // 테이블 일대다 관계는 항상 다쪽에 외래 키가 존재 -> 객체와의 차이때문에 관계가 안맞음
                    // 그리고 일대다 단방향 시 반드시 @JoinColumn을 해줘야 함
                    // -> 그렇지 않으면 조인 테이블 방식 사용, 이 경우 TEAM_MEMBER 라는 중간 테이블이 생성되어 버림(12:00 일대다 참고)
//                    Member member = new Member();
//                    member.setUsername("member1");
//
//                    em.persist(member);
//
//                    Team team = new Team();
//                    team.setName("teamA");
//
//                    team.getMembers().add(member);
//
//                    em.persist(team);
                    
                    // 저장
                    Team team = new Team();
                    team.setName("TeamA");
                    em.persist(team);
    
                    Member member = new Member();
                    member.setUsername("member1");
                    // 영속 상태(persist)가 되면 Id가 자동 생성됨
                    // member.changeTeamId(team.getId());
                    member.changeTeam(team);
                    em.persist(member);
                    
                    em.flush();
                    em.clear();
                    
                    
    
                    // 이때 DB에 등록
                    // flush 발생 -> commit, jpql 쿼리 실행 시 발생
                    // em.setFlushMode(FlushModeType.COMMIT) 설정 시 jpql에서 실행 안됨
                    // 직접 호출 가능 em.flush()
                    // 1. Dirty Checking
                    // 2. 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
                    // 3. 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송
                    // (등록, 수정, 삭제)
                    tx.commit();
                } catch (Exception e){
                    tx.rollback();
                } finally {
                    em.close();
                }
            }
        }
        emf.close();
    }
}

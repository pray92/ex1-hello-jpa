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
    
                    Member member = new Member(200L, "member200");
                    em.persist(member);
                    
                    em.flush(); // 강제 호출 -> 1차 캐시는 유지, 변경 내용을 DB에 동기화
                    System.out.println("==============");
                    
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

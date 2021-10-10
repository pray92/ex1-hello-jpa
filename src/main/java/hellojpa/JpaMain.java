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
                    Member member = em.find(Member.class, 1L);      // 조회
                    // em.remove(member);                               // 삭제
                    // member.setName("HelloJPA");                     // 업데이트(persist 불필요)
                    // em.persist(member);
                    
                    // 전체 회원 검색
                    /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();*/
                    
                    // 5번째부터 8개까지 검색
                    List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .setFirstResult(5)
                            .setMaxResults(8)
                            .getResultList();
                    for(Member mem : result){
                        System.out.println("member.name = " + mem.getName());
                    }
    
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

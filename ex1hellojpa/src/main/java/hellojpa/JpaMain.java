package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

//            등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("test2");
//
//            entityManager.persist(member);


//            검색
//            Member findMember = entityManager.find(Member.class, 1L);
//            System.out.println("id : "+findMember.getId());
//            System.out.println("name : "+findMember.getName());

            // 검색2
            List<Member> members = entityManager
                    .createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .getResultList();
            for (Member member : members) {
                System.out.println(member.getId());
                System.out.println(member.getName());
            }

//            수정
//            persist를 안해줘도 객체가 변경되면 수정 쿼리가 나감. 트랜잭션 커밋 단계에서 변경점을 파악 후 수정 쿼리를 날리는 듯
//            Member findMember = entityManager.find(Member.class, 1L);
//            findMember.setName("test2");

            // 삭제
//            Member findMember = entityManager.find(Member.class, 1L);
//            entityManager.remove(findMember);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }

}

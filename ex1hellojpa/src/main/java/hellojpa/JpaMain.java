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
            // 비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("test3");

            // 영속
            System.out.println("==============BEFORE=================");
            entityManager.persist(member);
            System.out.println("==============AFTER=================");

            // find를 하면 제일 먼저 1차 캐시에서 찾음
            // 3L는 아직 디비에 저장되어있지는 않지만 1차 캐시에 있기 때문에 1차 캐시에서 가져옴.
            Member findMember = entityManager.find(Member.class, 3L);

            System.out.println("id : "+findMember.getId());
            System.out.println("name : "+findMember.getName());

            // 커밋할 때 쓰기 지연 SQL 저장소에 있는 걸 디비에 날림
            // 그래서 이때 insert 쿼리가 출력
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }

}

package hellojpa;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        // 엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 함.
        transaction.begin();

        try {
            Team team = Team.builder().name("team1").build();
            entityManager.persist(team);
            Member member = Member.builder().name("user1").team(team).build();
            entityManager.persist(member);

            // 쿼리 확인하기 위해 플러시 후 클리어 처리함.
            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("team name :: " + findTeam.getName());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }

}

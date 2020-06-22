package com.atguigu.sssp.test;

import com.atguigu.sssp.entity.Department;
import com.atguigu.sssp.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SSSPTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void testSSSP(){
    }

    @Test
    public void testJpaSecondLevelCache(){
     /*   String jpql = "FROM Department d";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        List<Department> departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        entityManager.close();*/
    }

    @Test
    public void testRepositorySecondLevelCache(){
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }
}

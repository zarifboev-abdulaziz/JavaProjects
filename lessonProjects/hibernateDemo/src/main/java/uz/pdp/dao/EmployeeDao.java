package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Employee;
import uz.pdp.repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao {
//    @Autowired
//    public SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {

        Session session = entityManager.unwrap(Session.class);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

//    public List<Employee> getEmployees() {
//        List<Employee> all = employeeRepository.findAll();
//        return all;
//    }

}

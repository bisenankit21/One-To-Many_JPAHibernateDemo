package com.ankit.JPAHibernateDemo.dao;

import com.ankit.JPAHibernateDemo.entity.Course;
import com.ankit.JPAHibernateDemo.entity.Instructor;
import com.ankit.JPAHibernateDemo.entity.InstructorDetail;
import com.ankit.JPAHibernateDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    //Define field for entity manager

    private final EntityManager entityManager;
    //inject entity manager using constructor injection

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor = entityManager.find(Instructor.class,id);
        //get the course
    List<Course> courses = instructor.getCourses();
        //break association of a; courses for the instructor
        for (Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        //remove the associate object referene
        // break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        //Create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data",id);
        //execute the query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        //creating query

        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetail "
                        + "where i.id = :data ", Instructor.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor); //will update the existing entity


    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);   //will save courses and associated reviews bacuse of cascadeType.all
    }

    @Override
    public Course findCourseAndReviewByCourseId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                        "JOIN FETCH c.review "+
                        "where c.id = :data",
                Course.class);
        query.setParameter("data", id);
        //execute query
        return query.getSingleResult();

    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query =  entityManager.createQuery(
                "select c from Course c " +
                "JOIN FETCH c.students "+
                "where c.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentid(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "JOIN FETCH s.courses "+
                        "where s.id = :data", Student.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}

package com.ankit.JPAHibernateDemo.dao;

import com.ankit.JPAHibernateDemo.entity.Course;
import com.ankit.JPAHibernateDemo.entity.Instructor;
import com.ankit.JPAHibernateDemo.entity.InstructorDetail;
import com.ankit.JPAHibernateDemo.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int id);
    void deleteInstructor(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void updateCourse(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);
    void save(Course theCourse);
    Course findCourseAndReviewByCourseId(int id );
    Course findCourseAndStudentByCourseId(int id);
    Student findStudentAndCoursesByStudentid(int id);
    void update(Student student);
    void deleteStudentById(int id);




}

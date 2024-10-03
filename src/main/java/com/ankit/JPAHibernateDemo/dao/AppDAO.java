package com.ankit.JPAHibernateDemo.dao;

import com.ankit.JPAHibernateDemo.entity.Instructor;
import com.ankit.JPAHibernateDemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int id);
    void deleteInstructor(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);


}

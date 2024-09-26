package com.ankit.JPAHibernateDemo;

import com.ankit.JPAHibernateDemo.dao.AppDAO;
import com.ankit.JPAHibernateDemo.entity.Instructor;
import com.ankit.JPAHibernateDemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class JpaHibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDemoApplication.class, args);

	}

	@Bean    //Executed after the Spring Beans have been loaded
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			 createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id =1;
		System.out.println("Deleting Instructor id :"+id);
		appDAO.deleteInstructor(id);
		System.out.println("Done !...");
	}

	private void findInstructor(AppDAO appDAO) {
		int id =2;
		System.out.println("Finding instructor id : "+ id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(" Instructotr : " + instructor);
		System.out.println(" The associate Instructor detail only : "+ instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
	/*
		//create the instructor
		Instructor tempInstructor = new Instructor("Ankit","Bisen","bisenankit21@gmail.com");

		//create the instructir details
		InstructorDetail tempInstructorDetails = new InstructorDetail("https://www.youtube.com/@bisenankit21","Teaching");

		*/
		Instructor tempInstructor = new Instructor("Dhruv","Bisen","bisenankit21@gmail.com");

		//create the instructir details
		InstructorDetail tempInstructorDetails = new InstructorDetail("https://www.youtube.com/@bisenankit21","Playing Chess");

		tempInstructor.setInstructorDetail(tempInstructorDetails);

		//Save the instructor
		//Note:- This will also save the details object because of CascadeType.ALL
		System.out.println("Saving Instructor " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done");

	}

}

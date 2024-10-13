package com.ankit.JPAHibernateDemo;

import com.ankit.JPAHibernateDemo.dao.AppDAO;
import com.ankit.JPAHibernateDemo.entity.Course;
import com.ankit.JPAHibernateDemo.entity.Instructor;
import com.ankit.JPAHibernateDemo.entity.InstructorDetail;
import com.ankit.JPAHibernateDemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class JpaHibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDemoApplication.class, args);

	}

	@Bean    //Executed after the Spring Beans have been loaded
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
		// createInstructor(appDAO);
			//findInstructor(appDAO);
		//	deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			// createInstructorWithCourse(appDAO);
		//	findInstructorWithCourse(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);

			//deleteCourse(appDAO);

			createCourseAndReviews(appDAO);


		};
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Pacman--the Story");
		//add some review

		tempCourse.addReview(new Review("Great Course...loved it!!"));
		tempCourse.addReview(new Review(" Cool Course...loved it, well done!!"));
		tempCourse.addReview(new Review("Bad Course...hate this, not good!!"));

		//save the course/.... and laverege the cascade all

		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);
		System.out.println("Done!!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting Course by Id"+ id);
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int id =10;
		Course course = appDAO.findCourseById(id);
		course.setTitle("Book my Show");
		System.out.println("Updating the course");
		appDAO.updateCourse(course);
		System.out.println("Done!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding the instructor id : "+ id);
		Instructor instructor =appDAO.findInstructorById(id);

		//update the instcrutor
		System.out.println("Updateing instructor id :"+ id);
		instructor.setLastName("Patle");
		appDAO.update(instructor);
		System.out.println("Done!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding instructor id: "+id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("instructor :"+instructor);
		System.out.println("Associated courses "+instructor.getCourses());
		System.out.println("Done !!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id =1;
		System.out.println("Finding instructor id : " +id);
		Instructor instructor =appDAO.findInstructorById(id);
		System.out.println("Instructor " + instructor);
		//find courses for instructor
		System.out.println("Finding courses for instcruor id : " +id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		//associated the objects
		instructor.setCourses(courses);
		System.out.println( " The assoxuated courses  "+ instructor.getCourses());

	}

	private void findInstructorWithCourse(AppDAO appDAO) {
		int id =1;
		System.out.println("Finding instructor id : " +id);
		Instructor instructor =appDAO.findInstructorById(id);
		System.out.println("Instructor " + instructor);
		System.out.println("The associated courses : "+instructor.getCourses());
		System.out.println("Done!!!");
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Ankit","Bisen","bisenankit21@gmail.com");

		//create the instructir details
		InstructorDetail tempInstructorDetails = new InstructorDetail("https://www.youtube.com/@bisenankit21","Playing Chess");

		tempInstructor.setInstructorDetail(tempInstructorDetails);
		//create some courses
		Course course1 = new Course("The new life1");
		Course course2 = new Course("New learning way of english");

		//add those cousrse to instrucrit
		tempInstructor.add(course1);
		tempInstructor.add(course2);
		//save instructor
		// this will also save the courses beacuse of cascadeype.PERSSIT

		System.out.println("saving instructor :"+ tempInstructor);
		System.out.println("The Course : "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id =3;
		System.out.println("Deleting Instructor detail having id :" +id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id=2;
		System.out.println("Instructor Detail for this Id :"+ id + " is");
		 InstructorDetail instructorDetail= appDAO.findInstructorDetailById(id);
		System.out.println(instructorDetail);
		System.out.println("The associated instructor is : "+instructorDetail.getInstructor());
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

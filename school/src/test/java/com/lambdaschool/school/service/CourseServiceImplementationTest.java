package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
//import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.awt.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplementationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindCourseById() {

        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(2);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void save()
    {

        String courseName = "Learn to Yoyo";
        Instructor i1 = instructorService.findInstructorById(1);
        Course newCourse = new Course(courseName, i1);

        Course returnCourse = courseService.save(newCourse);

        assertNotNull(returnCourse);

        Course foundCourse = courseService.findCourseById(returnCourse.getCourseid());
        assertEquals(foundCourse.getCoursename(), returnCourse.getCoursename());
    }



}
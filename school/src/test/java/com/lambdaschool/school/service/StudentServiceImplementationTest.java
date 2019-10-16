package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
//import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
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
public class StudentServiceImplementationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void testFindCourseById() {
//
//        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
//    }

//    @Test (expected = EntityNotFoundException.class)
//    public void deleteNotFound()
//    {
//        courseService.delete(100);
//        assertEquals(6, courseService.findAll().size());
//    }
//
//    @Test
//    public void deleteFound()
//    {
//        courseService.delete(2);
//        assertEquals(5, courseService.findAll().size());
//    }

    @Test
    public void save()
    {

        String studentName = "James";

        Student newStudent = new Student(studentName);


        Student returnStudent = studentService.save(newStudent);

        assertNotNull(returnStudent);

        Student foundStudent = studentService.findStudentById(returnStudent.getStudid());
        assertEquals(foundStudent.getStudname(), returnStudent.getStudname());
    }



}

package com.lambdaschool.school;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.controller.CourseController;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {

        Instructor instructor1 = new Instructor("Don");
        instructor1.setInstructid(1);
        Instructor instructor2 = new Instructor("Bob");
        instructor2.setInstructid(2);
        Instructor instructor3 = new Instructor("Jane");
        instructor3.setInstructid(3);
        Course course1 = new Course("Yoyo", instructor1);
        course1.setCourseid(4);
        Course course2 = new Course("Juggling", instructor2);
        course2.setCourseid(5);
        Course course3 = new Course("Dance", instructor3);
        course3.setCourseid(6);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);




    }



    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";


        System.out.println("SIZE: " + courseList.size());
        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        // the following actually performs a real controller call
        System.out.println(rb);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        System.out.println("GET endpoint accessed?");
//        System.out.println(r.getResponse().getContentAsString());
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }

}

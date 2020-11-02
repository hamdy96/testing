package org.acme.resteasy.Resources;

import org.acme.resteasy.model.Student;
import org.acme.resteasy.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StudentResourceTest {
	
	StudentService ss = new StudentService();
	
	@Test
	void TestGetAll() {
		ss = mock(StudentService.class);
	    List<Student> expected = new ArrayList<Student>(Arrays.asList(
	            new Student(1L, "ahmed", 2005, "CSC"),
	            new Student(2L, "hassan", 2006, "MCM"),
	            new Student(3L, "hussien", 2004, "CSC"),
	            new Student(4L, "ali", 2005, "MCM"),
	            new Student(5L, "hatem", 2006, "CSC")));
	    when(ss.getAllStudents()).thenReturn(expected);
	    List<Student> actual = ss.getAllStudents();
	    verify(ss, times(1)).getAllStudents();
	    assertEquals(expected.size(), actual.size(), "The returned size must be the same as Expected");
	}
	
	@Test
	void TestPagination() {
	    ss = mock(StudentService.class);
	    List<Student> expected = new ArrayList<Student>(Arrays.asList(
	            new Student(1L, "ahmed", 2005, "CSC"),
	            new Student(2L, "hassan", 2006, "MCM"),
	            new Student(3L, "hussien", 2004, "CSC")));
	    
	    when(ss.getAllStudentsPagination(0, 3)).thenReturn(expected);
	    List<Student> actual = ss.getAllStudentsPagination(0, 3);
	    verify(ss, times(1)).getAllStudentsPagination(0, 3);
	    assertEquals(expected.size(), actual.size(), "The returned size must be the same as Expected");
	}
	
	@Test
	void TestInvaildSize() {
	    ss = Mockito.spy(StudentService.class);
	    assertThrows(WebApplicationException.class, () -> {
	        ss.getAllStudentsPagination(0, -3);
	    });
	}
	
	@Test
	void TestDelete()
	{
	    ss = Mockito.spy(StudentService.class);
	    
	    List<Student> expected = new ArrayList<Student>(Arrays.asList(
	            new Student(1L, "hassan", 2006, "CS"),
	            new Student(2L, "AHMED", 2002, "BUS"),
	            new Student(4L, "Youmna", 2005, "ENG"),
	            new Student(5L, "Hatem", 2001, "MCM")));
	    
	    ss.deleteStudent(3);
	    
	    List <Student> actual =  ss.getAllStudents();
	    assertEquals(4,actual.size(),"The returned size must be the same as Expected");
	    assertEquals(Arrays.toString(new List[]{expected}), Arrays.toString(new List[]{actual}),
	            "Student lists should be the same");
	}

}


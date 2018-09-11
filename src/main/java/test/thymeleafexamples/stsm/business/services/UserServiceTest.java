package test.thymeleafexamples.stsm.business.services; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import thymeleafexamples.stsm.business.services.UserService;

/** 
* UserService Tester. 
* 
* @author <Authors name> 
* @since <pre>Sep 11, 2018</pre> 
* @version 1.0 
*/ 
public class UserServiceTest { 

    UserService userService = new UserService();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: find(String userName) 
* 
*/ 
@Test
public void testFind() throws Exception { 
//TODO: Test goes here...

    System.out.println(userService.find("123").getUserName());
} 


} 

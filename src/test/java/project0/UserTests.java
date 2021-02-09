package project0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {
	
	@InjectMocks
	public UserService userSrv;
	
	@Mock
	public UserDao udao;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.openMocks(this);
		User mockUser = new User();
		mockUser.setUsername("testuser");
		mockUser.setPassword("testpassword");
		when(udao.getUser(anyString(), anyString())).then(invocation -> {
			String uname = invocation.getArgument(0);
			String pw = invocation.getArgument(1);
			if (uname.equals("testuser") && pw.equals("testpassword")) {
				return mockUser;
			} else {
				return null;
			}
		});
	}
	
	/*
	 * This test uses mocking to test that the DAO layer is being called once to login
	 */
	@Test
	public void testLoginWithValidCredentials() {
		String username = "testuser";
		String pw = "testpassword";
		User user = userSrv.login(username, pw);
		assertNotNull(user);
		verify(udao, times(1)).getUser(username, pw);
	}
	
	/*
	 * This is an example of a negative test. Here, we want to ensure that we are properly
	 * rejecting invalid credentials and preventing unauthorized logins
	 */
	@Test(expected=InvalidCredentialsException.class)
	public void testLoginWithInvalidCredentials() {
		String username = "wrong";
		String pw = "credentials";
		userSrv.login(username, pw);
	}
	
	@Test
	public void testRegistration() {
		String uname = "testuser";
		String pw = "testpassword";
		User newUser = new User();
		newUser.setUsername(uname);
		newUser.setPassword(pw);
		userSrv.register(newUser);
		User u = userSrv.login(uname, pw);
		assertNotNull(u);
		assertEquals(u.getUsername(), uname);
		assertEquals(u.getPassword(), pw);
		assertEquals(u.getAccounts().size(), 0);
	}
	
}

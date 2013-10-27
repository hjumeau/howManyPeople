package services;

import static org.junit.Assert.*
import howmanypeople.authentication.Role
import howmanypeople.authentication.UserAuthentication

import javax.servlet.http.HttpServletRequest

import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.security.core.userdetails.UserDetails

class UserAuthenticationServiceTest {

	def userAuthenticationService
	def springSecurityService
	
	@Before
	void setUp(){
		Role role = new Role(authority: Role.ROLE_DEFAULT)
		role.save()
	}
	
	@Test
	void createUser() {
		
		UserAuthentication userAuth = userAuthenticationService.saveUser('thomas','email','password')
		
		assertNotNull(userAuth)
		assertEquals('thomas', userAuth.username)
		assertEquals('email', userAuth.email)
		assertEquals(springSecurityService.encodePassword('password'), userAuth.password)
		
		UserAuthentication userAuthInDB = userAuthenticationService.loadUserByUsername(userAuth.username)
		
		assertNotNull(userAuth)
		assertEquals('thomas', userAuthInDB.username)
		assertEquals('email', userAuthInDB.email)
		assertEquals(springSecurityService.encodePassword('password'), userAuthInDB.password)
	}
	
	@Test	
	void autologinNewUser(){
		
		UserDetails userAuth = userAuthenticationService.saveUser('thomas','email','password')
		HttpServletRequest request = new MockHttpServletRequest()
		
		assertFalse(springSecurityService.isLoggedIn())
		
		userAuthenticationService.autoLogin(request, 'thomas', 'password', userAuth)	
		
		def sessionAuthentication = request.session.getAttribute('SPRING_SECURITY_CONTEXT').getAuthentication()
		
		assertTrue(springSecurityService.isLoggedIn())
		assertEquals(userAuth, springSecurityService.getPrincipal())
		assertEquals(sessionAuthentication.getPrincipal(), springSecurityService.getPrincipal())
	}

}

package howmanypeople.authentication

import howmanypeople.exception.DomainConstraintException

import javax.servlet.http.HttpServletRequest

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.context.HttpSessionSecurityContextRepository

class UserAuthenticationService implements GrailsUserDetailsService {

	private Logger log = LoggerFactory.getLogger(getClass())

	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
	 * we give a user with no granted roles this one which gets past that restriction but
	 * doesn't grant anything.
	 */
	static final List NO_ROLES = [
		new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)
	]

	/** Dependency injection for the application. */
	def grailsApplication

	def authenticationManager

	/**
	 * {@inheritDoc}
	 * @see org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
	 * #loadUserByUsername(java.lang.String, boolean)
	 */
	UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
		def conf = SpringSecurityUtils.securityConfig
		String userClassName = conf.userLookup.userDomainClassName
		def dc = grailsApplication.getDomainClass(userClassName)
		if (!dc) {
			throw new RuntimeException("The specified user domain class '$userClassName' is not a domain class")
		}

		Class<?> User = dc.clazz

		def query = "from ${dc.getName()} where ${conf.userLookup.usernamePropertyName} =:username or ${grailsApplication.config.userLookup.emailPropertyName} =:username"

		User.withTransaction { status ->
			def user = User.find(query,[username:username])
			if (!user) {
				log.warn "User not found: $username"
				throw new UsernameNotFoundException('User not found', username)
			}

			Collection<GrantedAuthority> authorities = loadAuthorities(user, username, loadRoles)
			createUserDetails user, authorities
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.core.userdetails.UserDetailsService
	 * #loadUserByUsername(java.lang.String)
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		loadUserByUsername username, true
	}

	/**
	 * Save spring security userAuthentication
	 * @param username
	 * @param email
	 * @param password
	 * @return userAuthentication
	 */
	UserDetails saveUser(String username, String email, String password) throws DomainConstraintException {
		def user = new User(username: username,password: password,email: email,enabled: true)
		if(user.save()){
            def role = Role.findWhere(authority:Role.ROLE_DEFAULT)
            Authorization.create(user, role, true)
            Collection<GrantedAuthority> authorities = loadAuthorities(user, username, true)
            createUserDetails user, authorities
        }else{
            throw new DomainConstraintException('Constraint of the user class are not respect', user.errors)
        }
	}

	/**
	 * Automatic login after successful registration.
	 * @param request
	 * @param username
	 */
	void autoLogin(HttpServletRequest request, String username, String password, UserDetails userDetails) {
		try {
			// Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, userDetails.authorities);
			Authentication authentication = authenticationManager.authenticate(token);
			log.debug('authenticate user',authentication.getPrincipal());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute(
				HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			log.error("Failure in autoLogin", e);
		}
	}

	protected Collection<GrantedAuthority> loadAuthorities(user, String username, boolean loadRoles) {
		if (!loadRoles) {
			return []
		}

		def conf = SpringSecurityUtils.securityConfig

		String authoritiesPropertyName = conf.userLookup.authoritiesPropertyName
		String authorityPropertyName = conf.authority.nameField

		Collection<?> userAuthorities = user."$authoritiesPropertyName"
		def authorities = userAuthorities.collect { new GrantedAuthorityImpl(it."$authorityPropertyName") }
		authorities ?: NO_ROLES
	}

	protected UserDetails createUserDetails(user, Collection<GrantedAuthority> authorities) {

		def conf = SpringSecurityUtils.securityConfig

		String usernamePropertyName = conf.userLookup.usernamePropertyName
		String emailPropertyName = grailsApplication.config.userLookup.emailPropertyName
		String passwordPropertyName = conf.userLookup.passwordPropertyName
		String enabledPropertyName = conf.userLookup.enabledPropertyName
		String accountExpiredPropertyName = conf.userLookup.accountExpiredPropertyName
		String accountLockedPropertyName = conf.userLookup.accountLockedPropertyName
		String passwordExpiredPropertyName = conf.userLookup.passwordExpiredPropertyName

		String username = user."$usernamePropertyName"
		String email = user."$emailPropertyName"
		String password = user."$passwordPropertyName"
		boolean enabled = enabledPropertyName ? user."$enabledPropertyName" : true
		boolean accountExpired = accountExpiredPropertyName ? user."$accountExpiredPropertyName" : false
		boolean accountLocked = accountLockedPropertyName ? user."$accountLockedPropertyName" : false
		boolean passwordExpired = passwordExpiredPropertyName ? user."$passwordExpiredPropertyName" : false

		new UserAuthentication(username, email, password, enabled, !accountExpired, !passwordExpired,
				!accountLocked, authorities, user.id)
	}

	protected Logger getLog() { _log }
}

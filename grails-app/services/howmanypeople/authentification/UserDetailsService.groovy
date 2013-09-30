package howmanypeople.authentification

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsService implements GrailsUserDetailsService {

	private Logger _log = LoggerFactory.getLogger(getClass())

	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
	 * we give a user with no granted roles this one which gets past that restriction but
	 * doesn't grant anything.
	 */
	static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

	/** Dependency injection for the application. */
	def grailsApplication

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
		String passwordPropertyName = conf.userLookup.passwordPropertyName
		String enabledPropertyName = conf.userLookup.enabledPropertyName
		String accountExpiredPropertyName = conf.userLookup.accountExpiredPropertyName
		String accountLockedPropertyName = conf.userLookup.accountLockedPropertyName
		String passwordExpiredPropertyName = conf.userLookup.passwordExpiredPropertyName

		String username = user."$usernamePropertyName"
		String password = user."$passwordPropertyName"
		boolean enabled = enabledPropertyName ? user."$enabledPropertyName" : true
		boolean accountExpired = accountExpiredPropertyName ? user."$accountExpiredPropertyName" : false
		boolean accountLocked = accountLockedPropertyName ? user."$accountLockedPropertyName" : false
		boolean passwordExpired = passwordExpiredPropertyName ? user."$passwordExpiredPropertyName" : false

		new GrailsUser(username, password, enabled, !accountExpired, !passwordExpired,
				!accountLocked, authorities, user.id)
	}

	protected Logger getLog() { _log }
}

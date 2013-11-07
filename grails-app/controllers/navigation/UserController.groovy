package navigation

import grails.converters.JSON
import howmanypeople.exception.DomainConstraintException

import java.text.MessageFormat

import org.springframework.web.servlet.support.RequestContextUtils;

class UserController {

	def springSecurityService
	def userService
	def userAuthenticationService

	def save() {
		try{

			def userDetails = userAuthenticationService.saveUser(params.username, params.email, params.password)
			userAuthenticationService.autoLogin(request, params.username, params.password, userDetails)
			def user = userService.buildUser(userDetails)
			render ([user: user, success: true] as JSON)

		}catch(DomainConstraintException ex){
			def errors = ex.errors?.getFieldErrors().collect{
				new MessageFormat(it.getDefaultMessage(), RequestContextUtils.getLocale(request)).format(it.getArguments())
			}
			render ([errors : errors] as JSON)
		}
	}

	def show() {
		if (springSecurityService.isLoggedIn()) {
			def userDetails = springSecurityService.getPrincipal()
			def user = userService.buildUser(userDetails)
			render user as JSON
		} else {
			render status: 401
		}
	}
}

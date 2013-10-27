package navigation

import grails.converters.JSON
import howmanypeople.authentication.UserAuthenticationService;

class UserController {

    def springSecurityService
    def userService
    def userAuthenticationService

    def save() {
        def userDetails = userAuthenticationService.saveUser(params.username, params.email, params.password)
		userAuthenticationService.autoLogin(request, params.username, params.password, userDetails)
		def user = userService.buildUser(userDetails)
        render ([user: user, success: true] as JSON)
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

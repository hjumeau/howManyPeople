package navigation

import grails.converters.JSON
import howmanypeople.authentication.UserAuthenticationService;

class UserController {

    def springSecurityService
    def userService
    def userAuthenticationService

    def save() {
        def user = userAuthenticationService.createUser(params.username, params.email, params.password)
		userAuthenticationService.autoLogin(request, user)
        render ([username: user.name, email: user.email, success: true] as JSON)
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

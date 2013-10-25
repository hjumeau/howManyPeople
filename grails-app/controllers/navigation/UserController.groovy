package navigation

import grails.converters.JSON

class UserController {

    def springSecurityService
    def userService

    def save() {
        def user = userService.createUser(params.username, params.email, params.password)
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

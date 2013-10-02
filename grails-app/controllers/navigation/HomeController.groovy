package navigation

import grails.converters.JSON

class HomeController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService
    def userService

	def index(){
		render(view:'/index')
	}

    def userDetails(){
        if (springSecurityService.isLoggedIn()) {
            def userDetails = springSecurityService.getPrincipal()
            def user = userService.buildUser(userDetails)
            render user as JSON
        }
        else {
            render status: 401
        }
    }
}

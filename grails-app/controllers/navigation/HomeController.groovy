package navigation

import grails.converters.JSON

class HomeController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService
    def consumerService

	def index(){
		render(view:'/index')
	}

    def userDetails(){
        if (springSecurityService.isLoggedIn()) {
            def userDetails = springSecurityService.getPrincipal()
            def consumer = consumerService.buildConsumer(userDetails)
            render consumer as JSON
        }
        else {
            render status: 401
        }
    }
}

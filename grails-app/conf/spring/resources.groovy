import howmanypeople.ConsumerService
import howmanypeople.authentification.UserDetailsService
import howmanypeople.authentification.UserDetailsService

// Place your Spring DSL code here
beans = {
	userDetailsService(UserDetailsService){
		grailsApplication = ref("grailsApplication")
	}

    ConsumerService(){}
}

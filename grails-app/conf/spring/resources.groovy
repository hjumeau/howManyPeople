import howmanypeople.authentication.UserAuthenticationService

// Place your Spring DSL code here
beans = {
	userDetailsService(UserAuthenticationService){
		grailsApplication = ref("grailsApplication")
	}

    ConsumerService(){}
}

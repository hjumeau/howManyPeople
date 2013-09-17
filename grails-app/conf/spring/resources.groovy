// Place your Spring DSL code here
beans = {
	userDetailsService(com.howmanypeople.authentication.UserDetailsService){
		grailsApplication = ref("grailsApplication")
	}
}

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"home", action:"index")
		"/user"(controller:"home", action:"userDetails")
		"500"(view:'/error')
	}
}

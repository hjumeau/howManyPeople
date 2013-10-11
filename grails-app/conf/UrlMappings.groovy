class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"home", action:"index")
		"/user"(controller:"user"){
            action = [GET: "show", POST:"save"]
        }
		"500"(view:'/error')
	}
}

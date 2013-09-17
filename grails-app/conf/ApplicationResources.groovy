modules = {
	core {
		defaultBundle 'core'
		resource url:'js/thirdparty/jquery.js'
		resource url:'js/thirdparty/handlebars.js'
		resource url:'js/thirdparty/ember.js'
	}
    application {
		dependsOn 'core'
		defaultBundle 'app'
        resource url:'js/Application.js'
		resource url:'js/Router.js'
		
		resource url:'js/templates/application.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/index.embbars', attrs:[type:'js'], bundle:'app'
        resource url:'js/IndexView.js'
        resource url:'js/ApplicationView.js'
    }
}
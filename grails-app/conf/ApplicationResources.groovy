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
        resource url:'js/authentication/User.js'
    	resource url:'js/authentication/AuthenticationController.js'
        resource url:'js/authentication/AuthenticatedRoute.js'
		resource url:'js/authentication/LoginController.js'
		resource url:'js/authentication/LoginRoute.js'
		resource url:'js/home/HomeRoute.js'
		resource url:'js/Router.js'

		resource url:'js/templates/application.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/header.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/login.embbars', attrs:[type:'js'], bundle:'app'

		resource url:'js/header/HeaderView.js'
		resource url:'js/authentication/LoginView.js'
		resource url:'js/home/HomeView.js'
        resource url:'js/ApplicationView.js'
    }
	style {
		resource url:'css/structure.less', attrs:[rel: "stylesheet/less", type:'css'], bundle:'bundle_style'
		resource url:'css/header.less', attrs:[rel: "stylesheet/less", type:'css'], bundle:'bundle_style'
		resource url:'css/needforbundle.css'
	}
}
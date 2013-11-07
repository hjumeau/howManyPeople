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
        resource url:'js/authentication/AuthenticatedRoute.js'
		resource url:'js/authentication/LoginController.js'
		resource url:'js/authentication/LoginRoute.js'
		resource url:'js/authentication/RegistrationRoute.js'
		resource url:'js/authentication/RegistrationController.js'
		resource url:'js/home/menu/HomeMenuController.js'
        resource url:'js/home/HomeController.js'
        resource url:'js/home/HomeRoute.js'
		resource url:'js/Router.js'

		resource url:'js/templates/application.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home-menu.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/authentication-layout.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/login.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/registration.embbars', attrs:[type:'js'], bundle:'app'

		resource url:'js/authentication/LoginView.js'
		resource url:'js/authentication/RegistrationView.js'
		resource url:'js/home/HomeView.js'
		resource url:'js/home/menu/HomeMenuView.js'
        resource url:'js/ApplicationView.js'
    }
	style {
        resource url:'css/structure.less', attrs:[rel: "stylesheet/less", type:'css'], bundle:'bundle_style'
        resource url:'css/authentication.less', attrs:[rel: "stylesheet/less", type:'css'], bundle:'bundle_style'
        resource url:'css/header.less', attrs:[rel: "stylesheet/less", type:'css'], bundle:'bundle_style'
        resource url:'css/bootstrap-alerts.css'
        resource url:'css/bootstrap-btn.css'
        resource url:'css/bootstrap-form.css'
        resource url:'fonts/icons/style.css'
        resource url:'css/component.css'
		resource url:'css/needforbundle.css'
	}
}
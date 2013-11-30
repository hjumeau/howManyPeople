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
        resource url:'js/components/AppAlertsComponent.js'
        resource url:'js/authentication/AuthenticatedRoute.js'
		resource url:'js/authentication/AuthenticationLoginController.js'
		resource url:'js/authentication/AuthenticationRegistrationController.js'
		resource url:'js/authentication/AuthenticationRoute.js'
		resource url:'js/home/menu/HomeMenuController.js'
        resource url:'js/home/HomeController.js'
        resource url:'js/home/HomeRoute.js'
		resource url:'js/Router.js'

		resource url:'js/templates/application.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/components/app-alerts.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home/home.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home/home-menu.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home/index.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/home/search.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/authentication.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/login.embbars', attrs:[type:'js'], bundle:'app'
		resource url:'js/templates/authentication/registration.embbars', attrs:[type:'js'], bundle:'app'

		resource url:'js/authentication/AuthenticationView.js'
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
    	resource url:'css/bootstrap-navbar.css'
        resource url:'fonts/icons/style.css'
        resource url:'css/nexus-menu.css'
		resource url:'css/needforbundle.css'
	}
}
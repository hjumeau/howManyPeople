App.RegistrationRoute = Ember.Route.extend({
	beforeModel : function(transition) {
		if (this.get('currentUser').isAuthenticate()) {
			this.transitionTo('home');
		}
	},
	setupController : function(controller, context) {
		controller.reset();
	}
});
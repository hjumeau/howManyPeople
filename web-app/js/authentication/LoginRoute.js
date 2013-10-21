App.LoginRoute = Ember.Route.extend({
	beforeModel : function(transition) {
		if (App.User.isAuthenticate()) {
			this.transitionTo('home');
		}
	}
});
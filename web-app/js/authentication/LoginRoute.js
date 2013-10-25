App.LoginRoute = Ember.Route.extend({
	beforeModel : function(transition) {
		if (this.get('currentUser').isAuthenticate()) {
			this.transitionTo('home');
		}
	}
});
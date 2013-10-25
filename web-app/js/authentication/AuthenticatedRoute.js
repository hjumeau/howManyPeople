App.AuthenticatedRoute = Ember.Route.extend({

	beforeModel : function(transition) {
		if (!this.get('currentUser').isAuthenticate()) {
            this.redirectToLogin(transition);
		}
	},

	redirectToLogin : function(transition) {
		var loginController = this.controllerFor('login');
		loginController.set('attemptedTransition', transition);
		this.transitionTo('login');
	},

	actions : {
		error : function(reason, transition) {
			if (reason.status === 401) {
				this.redirectToLogin(transition);
			} else {
				this.transitionTo('login', {
					errorMessage : reason
				});
			}
		}
	}
});
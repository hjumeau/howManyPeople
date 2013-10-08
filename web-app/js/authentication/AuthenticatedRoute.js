App.AuthenticatedRoute = Ember.Route.extend({

	beforeModel : function(transition) {
		if (!App.User.isAuthenticate()) {
			return $.getJSON('/HowManyPeople/user').then(function(data){
				App.User.setProperties(data);
			});
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
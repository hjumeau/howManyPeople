App.RegistrationController = Ember.Controller.extend({

	reset : function() {
		this.setProperties({
			username : "",
			password : "",
			errorMessage : ""
		});
	},

	actions : {
		register : function() {

			var self = this;
			var data = this.getProperties('username', 'email', 'password');
			// Clear out any error messages.
			// this.set('errorMessage', null);

			$.post('/HowManyPeople/user', data).then(function(response) {

				// self.set('errorMessage', response.message);
				if (response.success) {
					self.get('currentUser').setProperties(response.user);
					self.transitionToRoute('home');
				}
			});
		},
		goToLog : function() {
			this.transitionToRoute('login')
		}
	}
});
App.HomeMenuController = Ember.ObjectController.extend({
	actions : {
		logOut : function() {
			var self = this;
			$.get('/HowManyPeople/logout').done(function() {
				self.get('currentUser').reset();
				self.transitionToRoute('login');
			})
		}
	}
});
App.Router.map(function() {
	this.route('home');
	this.resource('login');
	this.route('registration');
});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('home')
	}
});

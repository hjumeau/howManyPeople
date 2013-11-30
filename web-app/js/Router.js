App.Router.map(function() {
	this.resource('home', function(){
		this.route('search');
	});
	this.resource('authentication', function(){
		this.route('login');
		this.route('registration');
	});
});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('home.search')
	}
});

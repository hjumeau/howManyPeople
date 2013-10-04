App.Router.map(function() {
	this.route('home');
	this.route('login');
});

App.ApplicationRoute = Ember.Route.extend({
	model : function() {
		var user = $.getJSON('/HowManyPeople/user', {}).done(function(data) {
			return App.User.create(data);
		}).fail(function(xhr, textStatus, errorThrown) {
			return App.User.create();
		})
		var promise = Ember.Deferred.create();
		var result = promise.resolve(user);
		return result;	
	}
});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('home')
	}
});

App.HomeRoute = App.AuthenticatedRoute.extend({
	model : function() {
		return [ 'red', 'yellow', 'blue' ];
	}
});

App.LoginRoute = Ember.Route.extend({
	beforeModel : function(transition) {
		if (this.modelFor('application').isAuthenticated()) {
			this.transitionTo('home');
		}
	}
});

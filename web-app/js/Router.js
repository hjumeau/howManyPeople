App.Router.map(function() {
	this.route('home');
	this.route('login');
});

App.ApplicationRoute = Ember.Route.extend({
	init: function(){
		App.User = App.User.create();
	}

});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('home')
	}
});

App.HomeRoute = App.AuthenticatedRoute.extend({});

App.HomeIndexRoute = Ember.Route.extend({
	model : function() {
		return [ 'red', 'yellow', 'blue' ];
	}
});
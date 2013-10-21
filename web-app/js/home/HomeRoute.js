App.HomeRoute = App.AuthenticatedRoute.extend({
	model : function() {
		return [ 'red', 'yellow', 'blue' ];
	}
});
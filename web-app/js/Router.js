App.Router.map(function() {
    this.route('home');
    this.route('login');
});


App.IndexRoute = Ember.Route.extend({
  redirect:function() {
    this.transitionTo('home')
  }
});

App.HomeRoute = App.AuthenticatedRoute.extend({
    model: function() {
        return ['red', 'yellow', 'blue'];
    }
});


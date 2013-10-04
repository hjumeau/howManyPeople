App.Router.map(function () {
    this.route('home');
    this.route('login');
});

App.ApplicationRoute = Ember.Route.extend({
    model: function () {
        var user = App.User.create();
        $.getJSON('/HowManyPeople/user', {}).done(function(data){
            if (!Em.isEmpty(data)) {
                user.setProperties(data);
            }
        })
        return user;
    },
    setupController: function (controller, user) {
        this.controllerFor('authentication').set('model', user);
    }
});

App.IndexRoute = Ember.Route.extend({
    redirect: function () {
        this.transitionTo('home')
    }
});

App.HomeRoute = App.AuthenticatedRoute.extend({
    model: function () {
        return ['red', 'yellow', 'blue'];
    }
});

App.LoginRoute = Ember.Route.extend({
    beforeModel: function(transition) {
        if (!this.controllerFor('authentication').get('user')) {
            this.transitionTo('home');
        }
    }
});

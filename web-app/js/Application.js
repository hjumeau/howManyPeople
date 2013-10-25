App = Ember.Application.create({
	LOG_TRANSITIONS: true
});


Ember.Application.initializer({
    name: "currentUser",

    initialize: function(container, application) {
        container.register('user:currentUser', App.User);

        // inject session in all controllers and routes
        container.typeInjection('controller', 'currentUser', 'user:currentUser');
        container.typeInjection('route', 'currentUser', 'user:currentUser');
    }
});

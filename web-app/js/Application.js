App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

Ember.Application.initializer({
    name: "ioc",

    initialize: function(container, application) {
        container.register('session:session', App.User);
        container.optionsForType('session', { singleton: true });

        // inject session in all controllers and routes
        container.typeInjection('controller', 'session', 'session:session');
        container.typeInjection('route', 'session', 'session:session');
    }
});
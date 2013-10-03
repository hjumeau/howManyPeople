App.AuthenticatedRoute = Ember.Route.extend({

    beforeModel: function(transition) {
        if (!this.controllerFor('login').get('user')) {
            this.redirectToLogin(transition);
        }
    },

    redirectToLogin: function(transition) {
        var loginController = this.controllerFor('login');
        loginController.set('attemptedTransition', transition);
        this.transitionTo('login');
    },

    events: {
        error: function(reason, transition) {
            if (reason.status === 401) {
                this.redirectToLogin(transition);
            } else {
                this.transitionTo('login',{errorMessage:reason});
            }
        }
    }
});
App.RegistrationController = Ember.Controller.extend({

    errors: null,

    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            email: "",
            errors: null
        });
    },

    actions: {
        register: function () {

            var self = this;
            var data = this.getProperties('username', 'email', 'password');
            // Clear out any error messages.
            this.set('errors', null);

            $.post('/HowManyPeople/user', data).then(function (response) {

                if (response.success) {
                    self.get('currentUser').setProperties(response.user);
                    self.transitionToRoute('home');
                } else {
                    self.set('errors',response.errors);
                }
            });
        },
        goToLog: function () {
            this.transitionToRoute('login')
        }
    }
});
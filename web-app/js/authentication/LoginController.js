App.LoginController = Ember.Controller.extend({

    errors: null,

    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            errors: null
        });
    },

    actions: {
        login: function () {

            var self = this;
            var data = {
                j_username: this.get('username'),
                j_password: this.get('password')
            };

            // Clear out any error messages.
            this.set('errors', null);

            $.post('/HowManyPeople/j_spring_security_check', data).then(function (response) {

                if (response.success) {
                    self.get('currentUser').setProperties(response.user);

                    var attemptedTransition = self.get('attemptedTransition');
                    if (attemptedTransition) {
                        self.set('attemptedTransition', null);
                        attemptedTransition.retry();
                    } else {
                        self.transitionToRoute('home');
                    }
                } else {
                    self.set('errors',[response.error]);
                }
            });
        },
        redirectToRegistration:function (){
            this.transitionToRoute('registration')
        }
    }
});
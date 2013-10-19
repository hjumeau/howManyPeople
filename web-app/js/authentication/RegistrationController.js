App.RegistrationController = Ember.Controller.extend({

    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            errorMessage: ""
        });
    },

    actions: {
        register: function () {

            var self = this;
            var data = this.getProperties('username','email','password');
            // Clear out any error messages.
            //this.set('errorMessage', null);

            $.post('/HowManyPeople/user', data).then(function (response) {

                //self.set('errorMessage', response.message);
                if (response.success) {
                    App.User.setProperties(response);

                    var attemptedTransition = self.get('attemptedTransition');
                    if (attemptedTransition) {
                        self.set('attemptedTransition', null);
                        attemptedTransition.retry();
                    } else {
                        self.transitionToRoute('home');
                    }
                }
            });
        },
        redirectToLogin:function (){
            this.transitionToRoute('login')
        }
    }
});
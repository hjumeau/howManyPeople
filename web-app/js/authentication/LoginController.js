App.LoginController = Ember.Controller.extend({

    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            errorMessage: ""
        });
    },

    user: null,
    /*tokenChanged: function() {
     localStorage.token = this.get('token');
     }.observes('token'),*/

    actions: {
        login: function () {

            var self = this;
            var data = {
                j_username: this.get('username'),
                j_password: this.get('password')
            };

            // Clear out any error messages.
            //this.set('errorMessage', null);

            $.post('/HowManyPeople/j_spring_security_check', data).then(function (response) {

                //self.set('errorMessage', response.message);
                if (response.success) {
                    self.set('user', response);

                    var attemptedTransition = self.get('attemptedTransition');
                    if (attemptedTransition) {
                        self.set('attemptedTransition', null);
                        attemptedTransition.retry();
                    } else {
                        // Redirect to 'articles' by default.
                        self.transitionToRoute('index');
                    }
                }
            });
        }
    }
});
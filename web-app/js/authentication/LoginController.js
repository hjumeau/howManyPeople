App.LoginController = Ember.Controller.extend({

	needs:['authentication'], 
	
    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            errorMessage: ""
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
            //this.set('errorMessage', null);

            $.post('/HowManyPeople/j_spring_security_check', data).then(function (response) {

                //self.set('errorMessage', response.message);
                if (response.success) {
                    self.get('controllers.authentication').set('model',response);

                    var attemptedTransition = self.get('attemptedTransition');
                    if (attemptedTransition) {
                        self.set('attemptedTransition', null);
                        attemptedTransition.retry();
                    } else {
                        self.transitionToRoute('home');
                    }
                }
            });
        }
    }
});
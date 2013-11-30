App.AuthenticationRegistrationController = Ember.Controller.extend({

    alert: null,

    reset: function () {
        this.setProperties({
            username: "",
            password: "",
            email: "",
            alert:null
        });
    },

    actions: {
        register: function () {

            var self = this;
            var data = this.getProperties('username', 'email', 'password');

            $.post('/HowManyPeople/user', data).then(function (response) {

                if (response.success) {
                    self.get('currentUser').setProperties(response.user);
                    self.transitionToRoute('home.search');
                } else {
                	self.set('alert',{connotation:'error', messages : response.errors});
                }
            });
        },
        goToLog: function () {
            this.transitionToRoute('authentication.login')
        }
    }
});
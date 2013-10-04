App.AuthenticationController = Ember.Controller.extend({
    currentUser: function() {
        return this.get('model').getProperties();
    }.property('model'),

    isAuthenticated: function() {
        return user.isAuthenticated()
    }.property('model.name')
});
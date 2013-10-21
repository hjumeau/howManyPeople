App.AuthenticationController = Ember.ObjectController.extend({
    
	currentUser: function() {
        return this.get('model').getProperties();
    }.property('model'),
    
    isAuthenticated: function() {
        return this.get('model').isAuthenticated();
    }.property('model')
});
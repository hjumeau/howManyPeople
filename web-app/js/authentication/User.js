App.User = Ember.Object.extend({
    name: null,
    email: null,
    isAuthenticate: function () {
        return !Ember.isEmpty(this.get('name'));
    }
});
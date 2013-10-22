App.HeaderController = Ember.ObjectController.extend({

    shouldDisplay: false,

    actions: {
        showMenu: function () {
            this.toggleProperty('shouldDisplay');
        }
    }
});
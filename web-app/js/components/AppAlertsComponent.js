App.AppAlertsComponent = Ember.Component.extend({
	connotation:null,
	messages:null,
	connotationStyle: function(){
		return 'alert-%@'.fmt(this.get('connotation'));
	}.property('connotation'),
	actions: {
		clearMessages: function() {
	      this.set('messages',null);
	    }
	  }
});
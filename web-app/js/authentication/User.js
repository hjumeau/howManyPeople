App.User = Ember.Object.extend({
	name : null,
	email : null,

	init : function() {
		var self = this;
		$.ajax({
			type : 'GET',
			url : '/HowManyPeople/user',
			dataType : 'json',
			success : function(data) {
				self.setProperties(data);
			},
			async : false
		});
	},
	
	isAuthenticate : function() {
		return !Ember.isEmpty(this.get('name'));
	},
	
	reset : function() {
		Ember.run.sync();
		Ember.run.next(this, function() {
			this.setProperties({
	            name: null,
	            email: null
	        });
		});
	}

});
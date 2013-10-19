package howmanypeople.authentication

class Role {

	static final ROLE_DEFAULT = 'ROLE_USER'
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}

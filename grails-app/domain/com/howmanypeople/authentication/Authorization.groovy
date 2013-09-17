package com.howmanypeople.authentication

import org.apache.commons.lang.builder.HashCodeBuilder

class Authorization implements Serializable {

	User user
	Role role

	boolean equals(other) {
		if (!(other instanceof Authorization)) {
			return false
		}

		other.user?.id == user?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static Authorization get(long userId, long roleId) {
		find 'from Authorization where user_id=:userId and role_id=:roleId',
			[userId: userId, roleId: roleId]
	}

	static Authorization create(User user, Role role, boolean flush = false) {
		new Authorization(user: user, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(User user, Role role, boolean flush = false) {
		Authorization instance = Authorization.findByUserAndRole(user, role)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM Authorization WHERE user=:user', [user: user]
	}

	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM Authorization WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}

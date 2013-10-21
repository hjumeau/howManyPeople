package howmanypeople

import howmanypeople.User

import org.springframework.security.core.userdetails.UserDetails

class UserService {

    User buildUser(UserDetails userDetails) {
        User user = new User(name: userDetails.username, email: userDetails.email)
        return user
    }

    User createUser(String username, def email, def password) {
        def user = new howmanypeople.authentication.User(username: username,
                password: password,
                email: email,
                enabled: true)
        user.save(flush: true)
        return new User(name: user.username, email: user.email)
    }
}

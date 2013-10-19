package howmanypeople

import howmanypeople.authentication.Authorization
import howmanypeople.authentication.Role

import org.springframework.security.core.userdetails.UserDetails

class UserService {

    User buildUser(UserDetails userDetails) {
        User user = new User(name: userDetails.username, email: userDetails.email)
        return user
    }
}

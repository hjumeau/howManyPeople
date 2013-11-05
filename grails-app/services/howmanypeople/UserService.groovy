package howmanypeople

import howmanypeople.model.User
import org.springframework.security.core.userdetails.UserDetails

class UserService {

    User buildUser(UserDetails userDetails) {
        User user = new User(name: userDetails.username, email: userDetails.email)
        return user
    }
}

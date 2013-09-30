package howmanypeople

import howmanypeople.User

import org.springframework.security.core.userdetails.UserDetails

class UserService {

    User buildConsumer(UserDetails userDetails) {
        User user = new User(name:userDetails.username, email:userDetails.email)
        return user
    }
}

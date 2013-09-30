package howmanypeople

import howmanypeople.authentication.User
import org.springframework.security.core.userdetails.UserDetails

class ConsumerService {

    Consumer buildConsumer(UserDetails userDetails) {
        Consumer consumer = new Consumer(name:userDetails.username, email:userDetails.email)
        return consumer
    }
}

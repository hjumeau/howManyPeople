package howmanypeople

import howmanypeople.authentication.User

class ConsumerService {

    Consumer getById(Long idUser) {
        User user = User.findWhere { id: idUser }
        Consumer consumer = new Consumer(name:user.username, email:user.email)
        return consumer
    }
}

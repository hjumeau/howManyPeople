import howmanypeople.authentication.Authorization
import howmanypeople.authentication.Role
import howmanypeople.authentication.User


class BootStrap {

    def init = { servletContext ->
        environments {
            production {
                def adminRole = new Role(authority: 'ROLE_ADMIN', description: 'Utilisateur').save()
                def userRole = new Role(authority: 'ROLE_USER', description: 'Utilisateur').save()

                def admin = new User(username: 'admin',
                        password: 'password',
                        email:'admin@howmanypeople.com',
                        enabled: true)
                admin.save()
                Authorization.create(admin, adminRole, true)

                def user = new User(username: 'harry',
                        password: 'password',
                        email:'harry@howmanypeople.com',
                        enabled: true)
                user.save()
                Authorization.create(user, userRole, true)
            }
        }
    }
    def destroy = {
    }
}

package hello.event

/**
 * @author 王成义
 * @version 1/16/17
 */
class LoginEvent {
    String user

    @Override
    String toString() {
        return "[user: $user]"
    }
}

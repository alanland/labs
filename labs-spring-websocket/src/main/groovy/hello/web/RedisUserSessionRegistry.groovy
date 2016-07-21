package hello.web

import hello.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent
import org.springframework.messaging.Message
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.user.*
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.util.Assert
import org.springframework.web.socket.messaging.*

import java.security.Principal
import java.util.concurrent.ConcurrentHashMap

/**
 * @author 王成义
 * @version 7/20/16
 */
//@Component('userRegistry')
public class RedisUserSessionRegistry extends DefaultSimpUserRegistry {

    static final String BOUNDED_HASH_KEY_PREFIX = "spring:websockets:users:";
    static final USER_PREFIX = "spring:websocket:users:"
    static final SESSION_PREFIX = "spring:websocket:users:"

    @Autowired
    RedisService redis

    @Override
    SimpUser getUser(String userName) {
        return null
    }

    @Override
    Set<SimpUser> getUsers() {
        return null
    }

    @Override
    Set<SimpSubscription> findSubscriptions(SimpSubscriptionMatcher matcher) {
        return null
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        AbstractSubProtocolEvent subProtocolEvent = (AbstractSubProtocolEvent) event;
        Message<?> message = subProtocolEvent.getMessage();
        SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, SimpMessageHeaderAccessor.class);
        String sessionId = accessor.getSessionId();

        if (event instanceof SessionSubscribeEvent) {
            DefaultSimpSession session = redis.getObject(SESSION_PREFIX + sessionId) as DefaultSimpSession;
            if (session != null) {
                String id = accessor.getSubscriptionId();
                String destination = accessor.getDestination();
                session.addSubscription(id, destination);
            }
        } else if (event instanceof SessionConnectedEvent) {
            Principal user = subProtocolEvent.getUser();
            if (user == null) {
                return;
            }
            String name = user.getName();
            if (user instanceof DestinationUserNameProvider) {
                name = ((DestinationUserNameProvider) user).getDestinationUserName();
            }
            synchronized (this) {
                DefaultSimpUser simpUser = redis.getObject(USER_PREFIX + name) as DefaultSimpUser
                if (simpUser == null) {
                    simpUser = new DefaultSimpUser(name, sessionId);
                    redis.setObject(USER_PREFIX+name, simpUser)
                } else {
                    simpUser.addSession(sessionId);
                }
                this.sessions.put(sessionId, (DefaultSimpSession) simpUser.getSession(sessionId));
            }
        } else if (event instanceof SessionDisconnectEvent) {
            synchronized (this) {
                DefaultSimpSession session = this.sessions.remove(sessionId);
                if (session != null) {
                    DefaultSimpUser user = session.getUser();
                    user.removeSession(sessionId);
                    if (!user.hasSessions()) {
                        this.users.remove(user.getName());
                    }
                }
            }
        } else if (event instanceof SessionUnsubscribeEvent) {
            DefaultSimpSession session = this.sessions.get(sessionId);
            if (session != null) {
                String subscriptionId = accessor.getSubscriptionId();
                session.removeSubscription(subscriptionId);
            }
        }
    }


    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public String toString() {
        return "users=" + this.users;
    }


    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return AbstractSubProtocolEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }


    private static class DefaultSimpUser implements SimpUser {

        private final String name;

        private final Map<String, SimpSession> sessions =
                new ConcurrentHashMap<String, SimpSession>(1);


        public DefaultSimpUser(String userName, String sessionId) {
            Assert.notNull(userName);
            Assert.notNull(sessionId);
            this.name = userName;
            this.sessions.put(sessionId, new DefaultSimpSession(sessionId, this));
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public boolean hasSessions() {
            return !this.sessions.isEmpty();
        }

        @Override
        public SimpSession getSession(String sessionId) {
            return (sessionId != null ? this.sessions.get(sessionId) : null);
        }

        @Override
        public Set<SimpSession> getSessions() {
            return new HashSet<SimpSession>(this.sessions.values());
        }

        void addSession(String sessionId) {
            DefaultSimpSession session = new DefaultSimpSession(sessionId, this);
            this.sessions.put(sessionId, session);
        }

        void removeSession(String sessionId) {
            this.sessions.remove(sessionId);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !(other instanceof SimpUser)) {
                return false;
            }
            return this.name.equals(((SimpUser) other).getName());
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }

        @Override
        public String toString() {
            return "name=" + this.name + ", sessions=" + this.sessions;
        }
    }

    private static class DefaultSimpSession implements SimpSession {

        private final String id;

        private final DefaultSimpUser user;

        private final Map<String, SimpSubscription> subscriptions = new ConcurrentHashMap<String, SimpSubscription>(4);


        public DefaultSimpSession(String id, DefaultSimpUser user) {
            Assert.notNull(id);
            Assert.notNull(user);
            this.id = id;
            this.user = user;
        }

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public DefaultSimpUser getUser() {
            return this.user;
        }

        @Override
        public Set<SimpSubscription> getSubscriptions() {
            return new HashSet<SimpSubscription>(this.subscriptions.values());
        }

        void addSubscription(String id, String destination) {
            this.subscriptions.put(id, new DefaultSimpSubscription(id, destination, this));
        }

        void removeSubscription(String id) {
            this.subscriptions.remove(id);
        }

        @Override
        public int hashCode() {
            return this.id.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !(other instanceof SimpSubscription)) {
                return false;
            }
            return this.id.equals(((SimpSubscription) other).getId());
        }

        @Override
        public String toString() {
            return "id=" + this.id + ", subscriptions=" + this.subscriptions;
        }
    }

    private static class DefaultSimpSubscription implements SimpSubscription {

        private final String id;

        private final DefaultSimpSession session;

        private final String destination;


        public DefaultSimpSubscription(String id, String destination, DefaultSimpSession session) {
            Assert.notNull(id);
            Assert.hasText(destination);
            Assert.notNull(session);
            this.id = id;
            this.destination = destination;
            this.session = session;
        }

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public DefaultSimpSession getSession() {
            return this.session;
        }

        @Override
        public String getDestination() {
            return this.destination;
        }

        @Override
        public int hashCode() {
            return 31 * this.id.hashCode() + getSession().hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !(other instanceof SimpSubscription)) {
                return false;
            }
            SimpSubscription otherSubscription = (SimpSubscription) other;
            return (getSession().getId().equals(otherSubscription.getSession().getId()) &&
                    this.id.equals(otherSubscription.getId()));
        }

        @Override
        public String toString() {
            return "destination=" + this.destination;
        }
    }


}
package cn.com.ittx.license.register

import de.codecentric.boot.admin.config.EnableAdminServer
import org.apache.commons.configuration.Configuration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration

/**
  * @author 王成义
  * @version 5/11/17
  */
@SpringBootApplication(exclude = Array(classOf[SessionAutoConfiguration]))
@EnableAdminServer
class Application {

//  import de.codecentric.boot.admin.notify.LoggingNotifier
//  import de.codecentric.boot.admin.notify.Notifier
//  import de.codecentric.boot.admin.notify.RemindingNotifier
//  import de.codecentric.boot.admin.notify.filter.FilteringNotifier
//  import org.springframework.context.annotation.Bean
//  import org.springframework.context.annotation.Configuration
//  import org.springframework.context.annotation.Primary
//  import org.springframework.context.annotation.Profile
//  import org.springframework.scheduling.annotation.Scheduled
//  import java.util.concurrent.TimeUnit
//
//  @Profile(Array("secure")) // tag::configuration-spring-security[]
//  @Configuration class SecurityConfig extends Nothing {
//    @throws[Exception]
//    protected def configure(http: HttpSecurity): Unit = { // Page with login form is served as /login.html and does a POST on /login
//      http.formLogin.loginPage("/login.html").loginProcessingUrl("/login").permitAll
//      // The UI does a POST on /logout on logout
//      http.logout.logoutUrl("/logout")
//      // The ui currently doesn't support csrf
//      http.csrf.disable
//      // Requests for the login page and the static assets are allowed
//      http.authorizeRequests.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll
//      // ... and any other request needs to be authorized
//      http.authorizeRequests.antMatchers("/**").authenticated
//      // Enable so that the clients can authenticate via HTTP basic for registering
//      http.httpBasic
//    }
//  }
//
//  // end::configuration-spring-security[]
//
//  @Configuration class NotifierConfig {
//    @Bean
//    @Primary def remindingNotifier: RemindingNotifier = {
//      val notifier = new RemindingNotifier(filteringNotifier(loggerNotifier))
//      notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10))
//      notifier
//    }
//
//    @Scheduled(fixedRate = 1 _000L) def remind(): Unit = {
//      remindingNotifier.sendReminders()
//    }
//
//    @Bean def filteringNotifier(delegate: Notifier) = new FilteringNotifier(delegate)
//
//    @Bean def loggerNotifier = new LoggingNotifier
//  }

}

object Application extends App {
  SpringApplication.run(classOf[Application])
}

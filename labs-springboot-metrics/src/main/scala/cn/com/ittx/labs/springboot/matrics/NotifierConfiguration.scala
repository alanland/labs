package cn.com.ittx.labs.springboot.matrics

/**
  * @author 王成义
  * @version 5/13/17
  */

import java.util.concurrent.TimeUnit

import de.codecentric.boot.admin.notify.filter.FilteringNotifier
import de.codecentric.boot.admin.notify.{Notifier, RemindingNotifier}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration, Primary}
import org.springframework.scheduling.annotation.{EnableScheduling, Scheduled}

@Configuration
@EnableScheduling
class NotifierConfiguration
(
  @Autowired val delegate: Notifier
) {

  @Bean def filteringNotifier: FilteringNotifier = new FilteringNotifier(delegate)

  @Bean
  @Primary
  def remindingNotifier: RemindingNotifier = {
    val remindingNotifier = new RemindingNotifier(filteringNotifier)
    remindingNotifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10))
    remindingNotifier
  }

  @Scheduled(fixedRate = 6000L)
  def remind(): Unit = {
    remindingNotifier.sendReminders()
  }
}
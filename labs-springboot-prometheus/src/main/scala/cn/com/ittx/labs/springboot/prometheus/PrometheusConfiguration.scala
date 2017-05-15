package cn.com.ittx.labs.springboot.prometheus

import io.prometheus.client.exporter.MetricsServlet
import io.prometheus.client.hotspot.{MemoryPoolsExports, StandardExports}
import io.prometheus.client.{Collector, CollectorRegistry}
import org.springframework.boot.autoconfigure.condition.{ConditionalOnClass, ConditionalOnMissingBean}
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.{Bean, Configuration}

/**
  * @author 王成义
  * @version 5/15/17
  */
@Configuration
@ConditionalOnClass(Array(classOf[CollectorRegistry]))
class PrometheusConfiguration {

  @Bean
  @ConditionalOnMissingBean def metricRegistry: CollectorRegistry = CollectorRegistry.defaultRegistry

  @Bean def registerPrometheusExporterServlet(metricRegistry: CollectorRegistry) =
    new ServletRegistrationBean(new MetricsServlet(metricRegistry), "/prometheus")


  @Bean def exporterRegister: ExporterRegister = {
    // StandardExport provides CPU usage metrics
    // MemoryPoolExports add memory usage by the JVM and host
    val collectors: List[Collector] = List(new StandardExports, new MemoryPoolsExports)
    new ExporterRegister(collectors)
  }

}

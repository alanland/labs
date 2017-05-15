package cn.com.ittx.labs.springboot.prometheus

import io.prometheus.client.Collector

/**
  * @author 王成义
  * @version 5/15/17
  */
class ExporterRegister {
  var collectors: List[Collector] = _

  def this(collectors: List[Collector]) = {
    this()
//    collectors.foreach(_.register())
//
    for (elem: Collector <- collectors) {
//      elem.register()
    }
    this.collectors = collectors
  }

}

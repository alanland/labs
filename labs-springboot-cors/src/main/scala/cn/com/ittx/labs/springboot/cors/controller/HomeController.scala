package cn.com.ittx.labs.springboot.cors.controller

import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * @author 王成义
  * @version 5/16/17
  */
@RestController
class HomeController {
  @RequestMapping(Array("/"))
  def index(): String = {
    "abc"
  }
}

package com.test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import com.test.Counter._
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.duration._

/**
 * Created by matheus on 10/9/15.
 */
class CounterTest extends TestKit(ActorSystem("CounterTest"))
  with WordSpecLike with Matchers with ImplicitSender{
  "Counter" must {

    val id = "123"

    var counter = system.actorOf(Counter.props, id)
    "increment up to 1000" in {
      (1 to 1000).foreach(_ => counter ! Inc)
      counter ! Get
      expectMsg(2 minutes, 1000)
    }
    "crash and recovery the counter" in {
      watch(counter)
      counter ! Shutdown
      expectTerminated(counter)
      counter = system.actorOf(Counter.props, id)
      counter ! Get
      expectMsg(2 minutes, 1000)
    }
  }
}

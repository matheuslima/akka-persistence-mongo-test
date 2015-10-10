package com.test

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.cluster.sharding.{ClusterShardingSettings, ClusterSharding}
import akka.testkit.{ImplicitSender, TestKit}
import com.test.Counter.{Get, Inc}
import org.scalatest.{Matchers, WordSpecLike}
import scala.concurrent.duration._

/**
 * Created by matheus on 10/9/15.
 */
class CounterTest extends TestKit(ActorSystem("CounterTest"))
  with WordSpecLike with Matchers with ImplicitSender{
  "Counter" must {
    "must increment correctly" in {
      val cluster = Cluster(system)
      cluster join cluster.selfAddress

      val counter = ClusterSharding(system).start(
        "Counter",
        Counter.props,
        ClusterShardingSettings(system),
        Counter.extractEntityId,
        Counter.extractShardId
      )

      (1 to 100).foreach( _ => counter ! Inc)
      counter ! Get
      expectMsg(10 seconds, 100)

    }
  }
}

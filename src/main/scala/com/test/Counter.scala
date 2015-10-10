package com.test

import akka.actor.Props
import akka.cluster.sharding.ShardRegion
import akka.persistence.PersistentActor

/**
 * Created by matheus on 10/9/15.
 */
object Counter {
  def props = Props[Counter]
  case object Inc
  case object Get
  case object Shutdown

  val extractEntityId: ShardRegion.ExtractEntityId = {
    case x => ("1", x)
  }
  val extractShardId: ShardRegion.ExtractShardId = {
    case _ => "1"
  }
}
class Counter extends PersistentActor{
  import Counter._
  var counter = 0

  override def receiveRecover: Receive = {
    case Inc => counter += 1
  }

  override def receiveCommand: Receive = {
    case Inc =>
      persist(Inc) { _ =>
        counter += 1
      }
    case Get => sender() ! counter
    case Shutdown => context stop self
  }

  override def persistenceId: String = self.path.name
}

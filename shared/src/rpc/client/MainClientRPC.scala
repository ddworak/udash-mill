package shared.rpc.client

import shared.rpc.client.chat.ChatNotificationsRPC
import io.udash.rpc._

trait MainClientRPC {
  def chat(): ChatNotificationsRPC
}

object MainClientRPC extends DefaultClientRpcCompanion[MainClientRPC]
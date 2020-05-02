package shared.rpc.server.secure

import shared.rpc.server.secure.chat.ChatRPC
import io.udash.rpc._

trait SecureRPC {
  def chat(): ChatRPC
}

object SecureRPC extends DefaultServerRpcCompanion[SecureRPC]

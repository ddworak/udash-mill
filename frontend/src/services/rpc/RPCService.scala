package frontend.services.rpc

import shared.rpc.client.MainClientRPC
import shared.rpc.client.chat.ChatNotificationsRPC

class RPCService(notificationsCenter: NotificationsCenter) extends MainClientRPC {
  override val chat: ChatNotificationsRPC =
    new ChatService(notificationsCenter.msgListeners, notificationsCenter.connectionsListeners)
}
package backend.rpc.secure

import backend.rpc.secure.chat.ChatEndpoint
import backend.services.DomainServices
import shared.model.auth.UserContext
import shared.rpc.server.secure.SecureRPC
import shared.rpc.server.secure.chat.ChatRPC

class SecureEndpoint(implicit domainServices: DomainServices, ctx: UserContext) extends SecureRPC {
  import domainServices._

  lazy val chatEndpoint = new ChatEndpoint

  override def chat(): ChatRPC = chatEndpoint
}

package frontend.views.chat

import frontend.routing.ChatState
import frontend.services.rpc.NotificationsCenter
import frontend.services.{TranslationsService, UserContextService}
import shared.model.SharedExceptions
import io.udash._

class ChatViewFactory(
  userService: UserContextService,
  translationsService: TranslationsService,
  notificationsCenter: NotificationsCenter
) extends ViewFactory[ChatState.type] {
  import scala.concurrent.ExecutionContext.Implicits.global

  override def create(): (View, Presenter[ChatState.type]) = {
    val model = ModelProperty[ChatModel](ChatModel(Seq.empty, "", 0))

    val rpc = userService.secureRpc()
    if (rpc.isEmpty) throw SharedExceptions.UnauthorizedException()

    val presenter = new ChatPresenter(model, rpc.get.chat(), userService, notificationsCenter)
    val view = new ChatView(model, presenter, translationsService)

    (view, presenter)
  }
}

package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import repositories.DataRepository

@Singleton
class ApiController @Inject()(cc: ControllerComponents, dataRepository: DataRepository)
  extends AbstractController(cc) {

  def index = Action {implicit  request =>
    Ok(views.html.index())
  }

  def ping = Action { implicit request =>
      Ok("Pong")
  }

  //get single post

  def  getPost(postId: Int) = Action { implicit request =>
    dataRepository.getPost(postId) map {post =>
      //if post found return 200 and data as json
      Ok(Json.toJson(post))
    } getOrElse NotFound  //otherwise return not found
  }

  def getComments(postId: Int) = Action {implicit request =>
    //return 200 with comment as json
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }
}
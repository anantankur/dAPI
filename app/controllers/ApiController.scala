package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import repositories.DataRepository
import controllers.Helpers._

//mongodb
import org.mongodb.scala._


@Singleton
class ApiController @Inject()(cc: ControllerComponents, dataRepository: DataRepository)
  extends AbstractController(cc) {

  // connection to mongodb
  val mongoClient: MongoClient = MongoClient() // without args will connect to localhost:27017
  val database: MongoDatabase = mongoClient.getDatabase("test")
  val collection: MongoCollection[Document] = database.getCollection("linkies")

  def index = Action {implicit  request =>
    Ok(views.html.index())
  }

  def ping = Action { implicit request =>
    collection.find().printResults()
    collection.count().printResults()
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
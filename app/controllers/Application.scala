package controllers

import java.io.File
import play.api.mvc._

object Application extends Controller {

  def index = Action { request => {
      Ok(views.html.index()(request))
    }
  }

  def upload = Action { request => {
      Redirect(routes.Application.index())
    }
  }

  def uploadFile = Action (parse.maxLength(parse.DefaultMaxDiskLength, parse.multipartFormData)) { request => {
      request.body match {
        case Left(MaxSizeExceeded(limit)) => {
          EntityTooLarge(s"file too large, limit is $limit")
        }
        case Right(multipartForm) => {
          multipartForm.file("files").map { f =>
            val file = new File(s"/tmp/${f.filename}")
            f.ref.moveTo(file)
            Ok("uploaded")
          }.getOrElse {
            BadRequest("meep!")
          }
        }
      }
    }
  }
}
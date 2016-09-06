package com.example

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import scala.util.Properties
import sys.process._

class ExampleController extends Controller {
  
  get("/healthz") { request: Request =>
    response.ok
  }

  get("/") { request: Request =>
    val whom : String = Properties.envOrElse("POWERED_BY", "Deis")
    val release : String = Properties.envOrElse("WORKFLOW_RELEASE", "unknown")
    val hostname = new StringBuilder
    val status = "hostname" ! ProcessLogger(hostname append _)

    s"Powered by $whom\nRelease $release on $hostname\n"
  }
}

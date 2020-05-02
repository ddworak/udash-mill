import mill._
import mill.scalalib._
import mill.scalajslib._

object Deps {
  val versionOfScala = "2.12.11"
  val versionOfScalaJs = "0.6.32"

  // Udash
  val udashVersion = "0.8.1"
  val udashJQueryVersion = "3.0.1"

  // Backend
  val jettyVersion = "9.4.20.v20190813"
  val logbackVersion = "1.2.3"
  val typesafeConfigVersion = "1.4.0"

  // JS dependencies
  val bootstrapVersion = "4.1.3"
  val highchartsVersion = "5.0.14"

  // Testing
  val scalatestVersion = "3.0.8"
  val scalamockVersion = "4.1.0"

  val shareDeps = Agg(
    ivy"io.udash::udash-core:$udashVersion",
    ivy"io.udash::udash-rpc:$udashVersion",
    ivy"io.udash::udash-rest:$udashVersion",
    ivy"io.udash::udash-i18n:$udashVersion",
    ivy"io.udash::udash-css:$udashVersion",
    ivy"io.udash::udash-auth:$udashVersion",
  )

  val frontendDeps = Agg(
    ivy"io.udash::udash-core:$udashVersion",
    ivy"io.udash::udash-rpc:$udashVersion",
    ivy"io.udash::udash-i18n:$udashVersion",
    ivy"io.udash::udash-css:$udashVersion",
    ivy"io.udash::udash-auth:$udashVersion",
    // type-safe wrapper for Twitter Bootstrap
    ivy"io.udash::udash-bootstrap4::$udashVersion",
    // type-safe wrapper for Highcharts
    ivy"io.udash::udash-charts::$udashVersion",
    // type-safe wrapper for jQuery
    ivy"io.udash::udash-jquery::$udashJQueryVersion",
  )

  val backendDeps = Agg(
    ivy"io.udash::udash-rpc:$udashVersion",
    ivy"io.udash::udash-rest:$udashVersion",
    ivy"io.udash::udash-i18n:$udashVersion",
    ivy"io.udash::udash-css:$udashVersion",
    ivy"org.eclipse.jetty:jetty-server:$jettyVersion",
    ivy"org.eclipse.jetty:jetty-rewrite:$jettyVersion",
    ivy"org.eclipse.jetty.websocket:websocket-server:$jettyVersion",
    ivy"com.typesafe:config:$typesafeConfigVersion",
    // server logging backend
    ivy"ch.qos.logback:logback-classic:$logbackVersion",
  )

  val frontendNativeDeps = Agg(
    ivy"org.webjars:bootstrap:$bootstrapVersion",
    ivy"org.webjars:highcharts:$highchartsVersion"
  )

}

object shared extends ScalaModule {
  def scalaVersion = Deps.versionOfScala

  def ivyDeps = Deps.shareDeps
}


object backend extends ScalaModule {
  def scalaVersion = Deps.versionOfScala

  def ivyDeps = Deps.backendDeps

  def moduleDeps = Seq(shared)

  override def mainClass = Some("backend.Launcher")
}

object frontend extends ScalaJSModule {
  def scalaVersion = Deps.versionOfScala

  def scalaJSVersion = Deps.versionOfScalaJs

  def moduleDeps = Seq(shared)

  def ivyDeps = Deps.frontendDeps ++ Deps.frontendNativeDeps
}

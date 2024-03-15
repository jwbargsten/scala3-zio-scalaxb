package org.bargsten.xb

import org.bargsten.xb.generated.*
import org.bargsten.xb.generated.`package`.defaultScope
import zio.*
import zio.test.{test, *}
import zio.test.Assertion.*

import java.io.IOException
import scala.xml.{NamespaceBinding, XML}
//import scalaxb.*

object XmlSpec extends ZIOSpecDefault:


  def buildAddress = {
    val a = Address(countryCode = "NL", zipCode = "1000AA", city = "Bargstricht", keyWord = "ab")
    //        scalaxb.toXML[Address](a, "abc", NamespaceBinding("kkkk", "", scala.xml.TopScope)).toString
    scalaxb.toXML[Address](a, "Address", defaultScope)
  }

  def sayIt: ZIO[Any, IOException, String] = ZIO.succeed(buildAddress).map(_.toString)

  def spec = suite("HelloWorldSpec")(
    test("sayHello correctly displays output") {
      for {
        res <- sayIt
      } yield assertTrue(res == "<Address xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><countryCode>NL</countryCode><zipCode>1000AA</zipCode><city>Bargstricht</city><keyWord>ab</keyWord></Address>")


    })

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.usergrid.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.apache.usergrid.datagenerators.{EntityDataGenerator, FeederGenerator}
import org.apache.usergrid.helpers.{Extractors,Utils}
import org.apache.usergrid.settings.{Headers, Settings}

import scala.util.parsing.json.JSONObject

/**
 * Provides CRUD methods for custom entities
 */

object EntityScenarios {

  val getEntity = exec(
    http("GET custom entity")
      .get(Settings.baseAppUrl+"/${collectionType}/${entityName}")
      .headers(Headers.authAnonymous)
      .check(status.is(200))
  )

  // not sure why I have to put stringToExpression -- thought String -> Expression[String] conversion would be automatic
  val putEntity = exec(
    http("Put custom entity")
      .put(stringToExpression(Settings.baseAppUrl + "/${collectionType}/${entityName}"))
      .body(StringBody("""${entity}"""))
      .headers(Headers.auth("${authType}"))
      .check(status.is(200))
  )


  val deleteEntity = exec(
    http("DELETE custom entity")
      .get(Settings.baseAppUrl + "/${collectionType}/${entityName}")
      .headers(Headers.auth("${authType}"))
      .check(status.is(200))
  )

  val postEntity = exec(
    http("Post custom entity")
      .post(stringToExpression(Settings.baseAppUrl + "/${collectionType}"))
      .body(StringBody("""${entity}"""))
      .headers(Headers.auth("${authType}"))
      .check(status.is(200))
  )

  /*
  val postEntityWithToken = exec(
    http("Post custom entity")
      .post(stringToExpression(Settings.baseAppUrl + "/${collectionType}"))
      .body(StringBody("""${entity}"""))
      .headers(Headers.authToken)
      .check(status.is(200))
  )

  val postEntityWithBasicAuth = exec(
    http("Post custom entity")
      .post(stringToExpression(Settings.baseAppUrl + "/${collectionType}"))
      .body(StringBody("""${entity}"""))
      .headers(Headers.authBasic)
      .check(status.is(200))
  )
  */

}

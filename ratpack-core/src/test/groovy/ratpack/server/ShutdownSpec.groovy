/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.server

import ratpack.test.internal.RatpackGroovyDslSpec
import spock.util.concurrent.PollingConditions

class ShutdownSpec extends RatpackGroovyDslSpec {

  private PollingConditions conditions = new PollingConditions()

  def "can invoke shutdown"() {
    when:
    app {
      handlers {
        get {
          get(Stopper).stop()
          response.send("ok")
        }
      }
    }

    then:
    def serverAddress = address.toString()
    createRequest().get(serverAddress).body.asString() == "ok"

    and:

    conditions.eventually { !server.running }

    when:
    createRequest().get(serverAddress)

    then:
    thrown ConnectException
  }

}

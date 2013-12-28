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

package ratpack.test.internal

import ratpack.groovy.Groovy
import ratpack.test.embed.EmbeddedApplication

abstract class RatpackGroovyScriptAppSpec extends EmbeddedRatpackSpec {

  @Delegate
  EmbeddedApplication application

  def setup() {
    application = createApplication()
  }

  abstract EmbeddedApplication createApplication()

  File getRatpackFile() {
    file("ratpack.groovy")
  }

  void script(String text) {
    ratpackFile.text = "import static ${Groovy.name}.ratpack\n\n$text"
  }

}

/*
 * Copyright 2022 Paul Butcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package ca.uwaterloo.flix.runtime.shell

import org.jline.reader.{EOFError, ParsedLine, Parser}
import org.jline.reader.Parser.ParseContext

import scala.jdk.CollectionConverters._

/**
  * Minimal implementation of `ParsedLine`, necessary to keep jline happy.
  * 
  * JLine uses these values to implement syntax highlighting, line continuation on 
  * unclosed string, brackets, etc. Because we have all of this functionality 
  * switched off, we just need to return something that conforms to the `ParsedLine`
  * interface.
  * 
  * https://github.com/jline/jline3/blob/master/reader/src/main/java/org/jline/reader/ParsedLine.java
  */
class Parsed(s: String) extends ParsedLine {

  def wordIndex(): Int = -1
  def word() = ""
  def wordCursor(): Int = -1
  def words() = Nil.asJava
  def cursor() = -1
  def line() = s
}

/**
  * Implementation of jline's `Parser` to trigger jline's line continuation functionality
  */
class ShellParser extends Parser {
  def parse(s: String, cursor: Int, context: Parser.ParseContext): ParsedLine = {
    
    // If the last character in the string is a backslash, throw `EOFError` to trigger line continuation
    if (s.lastOption == Some('\\'))
      throw new EOFError(-1, -1, "Escaped new line", "newline")

    new Parsed(s)
  }
}
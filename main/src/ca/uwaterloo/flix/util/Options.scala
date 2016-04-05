package ca.uwaterloo.flix.util

object Options {
  /**
    * Default options.
    */
  val Default = Options(
    debugger = Debugger.Disabled,
    Nil,
    verbosity = Verbosity.Silent,
    verify = Verify.Disabled,
    codegen = CodeGeneration.Disabled
  )
}

/**
  * General Flix options.
  *
  * @param debugger  enable or disable the built-in web-based debugger.
  * @param print     a list of things to print.
  * @param verbosity the level of verbosity.
  * @param verify    enable or disable the built-in verifier.
  * @param codegen   enable or disable JVM code generation
  */
case class Options(debugger: Debugger, print: List[String], verbosity: Verbosity, verify: Verify, codegen: CodeGeneration)


/**
  * An option to enable or disable the built-in web-based debugger.
  *
  * Note: Enabling the debugger may incur a significant performance overhead.
  */
sealed trait Debugger

object Debugger {

  /**
    * Enables the built-in web-based debugger.
    */
  case object Enabled extends Debugger

  /**
    * Disables the built-in web-based debugger.
    */
  case object Disabled extends Debugger

}

/**
  * An option to control the level of verbosity.
  */
sealed trait Verbosity

object Verbosity {

  /**
    * Output verbose information. Useful for debugging.
    */
  case object Verbose extends Verbosity

  /**
    * Output condensed information. The default.
    */
  case object Normal extends Verbosity

  /**
    * Output nothing. Useful for when Flix is used as a library.
    */
  case object Silent extends Verbosity

}

/**
  * An option to control whether verification is enabled.
  */
sealed trait Verify

object Verify {

  /**
    * Enables the built-in verifier.
    */
  case object Enabled extends Verify

  /**
    * Disables the built-in verifier.
    */
  case object Disabled extends Verify

}

sealed trait CodeGeneration

object CodeGeneration {

  /**
    * Enables JVM code generation of Flix functions.
    */
  case object Enabled extends CodeGeneration

  /**
    * Disables JVM code generation of Flix functions.
    */
  case object Disabled extends CodeGeneration
}

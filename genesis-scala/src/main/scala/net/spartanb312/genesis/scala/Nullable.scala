package net.spartanb312.genesis.scala

import scala.language.postfixOps

type Nullable[T <: Any] = T | Null

extension [T](t: T)
    def ?:(alternative: => T & Any): T & Any = if t != null then t else alternative
    
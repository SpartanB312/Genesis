package net.spartanb312.genesis.scala

import scala.quoted._

private def impl[A](using t: Type[A], ctx: Quotes): Expr[String] = Expr(Type.show[A])

inline transparent def typeName[A] = ${impl[A]}
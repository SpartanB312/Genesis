package net.spartanb312.genesis.scala

import scala.quoted._

type TypeName[A] = String
object TypeName extends TypeNamePlatform

trait TypeNamePlatform:
    inline transparent given [A]: TypeName[A] = ${TypeNamePlatform.impl[A]}
//noinspection ScalaWeakerAccess
private object TypeNamePlatform:
    def impl[A](using t: Type[A], ctx: Quotes): Expr[TypeName[A]] =
        Expr(Type.show[A])

inline transparent def typeName[A](using ev: TypeName[A]) = ev
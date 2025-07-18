package net.spartanb312.genesis.scala

import org.objectweb.asm.tree.{ClassNode, InsnList, MethodNode}
import org.objectweb.asm.{ClassWriter, Label, Opcodes}

import java.io.{File, FileOutputStream, FileWriter}
import java.util
import scala.compiletime.error
import scala.jdk.CollectionConverters.*


case class MethodBuilder(methodNode: MethodNode)


def INSTRUCTIONS(builder: InstructionBuilder ?=> Unit)(using methodBuilder: MethodBuilder): Unit =
    methodBuilder.methodNode.instructions.clear()
    given insnBuilder: InstructionBuilder(methodBuilder.methodNode.instructions)
    builder


def method(access: Access, name: String, description: String, signature: Nullable[String] = null, exceptions: Nullable[Array[String]] = null)(builder: MethodBuilder ?=> Unit): MethodNode =
    val methodNode = MethodNode(access, name, description, signature, exceptions)
    given MethodBuilder(methodNode)
    builder
    methodNode


def clinit(builder: MethodBuilder ?=> Unit): MethodNode =
    val methodNode = MethodNode(PRIVATE | STATIC, "<clinit>", "()V", null, null)
    given MethodBuilder(methodNode)
    builder
    methodNode


def constructor(access: Access, description: String, signature: Nullable[String], exception: Nullable[Array[String]] = null)(builder: MethodBuilder ?=> Unit): MethodNode =
    val methodNode = MethodNode(access, "<init>", description, signature, exception)
    given MethodBuilder(methodNode)
    builder
    methodNode


transparent inline def MethodNode(access: Access, name: String, description: String, signature: Nullable[String], exceptions: Nullable[Array[String]]): org.objectweb.asm.tree.MethodNode =
    org.objectweb.asm.tree.MethodNode(access.toInt, name, description, signature, exceptions)


inline def foo() = if (fib(28) != 317811) error("")

def main0(): Unit =
    foo()
    println(fib(1))
    println(fib(2))
    println(fib(3))
    println(fib(4))
    println(fib(5))
    println(fib(6))
    println(fib(7))
    println(fib(16))
    println(fib(28))
    val classNode1 = clazz(PUBLIC | STATIC, "Main"):
        CLINIT:
            INSTRUCTIONS:
                GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;")
                LDC("CLINIT")
                INVOKEVIRTUAL("java/io/PrintStream", "println", "(Ljava/lang/String;)V")
                RETURN

        CONSTRUCTOR(PUBLIC, "()V"):
            INSTRUCTIONS:
                ALOAD(0)
                INVOKESPECIAL("java/lang/Object", "<init>", "()V")
                GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;")
                LDC("Hello 1")
                INVOKEVIRTUAL("java/io/PrintStream", "println", "(Ljava/lang/String;)V")
                RETURN

        +method(PUBLIC | STATIC, "main", "([Ljava/lang/String;)V"):
            INSTRUCTIONS:

                +(TRY {
                    ICONST_1
                    TABLESWITCH(1 to 2, "labelA", "labelB", "labelC")
                    LABEL("labelA")
                    GOTO("labelD")

                    LABEL("labelB")
                    GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;")
                    LDC("Hello 11")
                    INVOKEVIRTUAL("java/io/PrintStream", "println", "(Ljava/lang/String;)V")
                    GOTO("labelD")

                    LABEL(L"labelC")
                    GOTO(L"labelD")

                    LABEL(L"labelD")
                    GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;")
                    LDC("End1")
                    INVOKEVIRTUAL("java/io/PrintStream", "println", "(Ljava/lang/String;)V")

                    NEW("java/lang/Exception")
                    DUP
                    LDC("hello")
                    INVOKESPECIAL("java/lang/Exception", "<init>", "(Ljava/lang/String;)V")
                    ATHROW
                } CATCH ("java/lang/Exception") apply {
                    POP
                    GETSTATIC("java/lang/System", "out", "Ljava/io/PrintStream;")
                    LDC("Exception1")
                    INVOKEVIRTUAL("java/io/PrintStream", "println", "(Ljava/lang/String;)V")
                })
                RETURN

    val writer = ClassWriter(ClassWriter.COMPUTE_FRAMES)
    classNode1.accept(writer)
    FileOutputStream("D:\\Main.class").write(writer.toByteArray)
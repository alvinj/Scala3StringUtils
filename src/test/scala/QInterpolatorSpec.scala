package com.alvinalexander.utils

import com.alvinalexander.utils.QInterpolator.Q

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object QInterpolatorSpec extends Properties("QInterpolatorSpec") {

    // see https://www.scala-exercises.org/scalacheck/generators
    // see https://booksites.artima.com/scalacheck/examples/html/ch06.html
    object GenString {

        val r = new scala.util.Random
        import StringUtils.genRandomVariableLengthStringWithBlankSpaces

        //TODO hack; need a better way to go from String -> Gen[String]
        def stringWithManyBlanks: Gen[String] = Gen.oneOf(
            genRandomVariableLengthStringWithBlankSpaces(r),
            genRandomVariableLengthStringWithBlankSpaces(r)
        )

        def genAlphaStringsLength0To10: Gen[String] = Gen.oneOf(
            for len <- 0 to 10 yield StringUtils.randomAlphanumericString(len)
        )

        /**
          * Ideas below here
          * ----------------
          */
        // val threeLetters: Gen[Seq[Char]] = Gen.pick(3, 'A' to 'Z')
        val genAlphaStream = Gen.containerOf[Stream,String](Gen.alphaStr)
        val evenInteger = Arbitrary.arbitrary[Int] suchThat (_ % 2 == 0)
        def badStateGen: Gen[String] = Gen.choose(73, 99).toString
        val genNonEmptyString: Gen[String] = Gen.alphaStr.suchThat(i => !i.isEmpty)
        val genNonEmptyNumString: Gen[String] = Gen.numStr.suchThat(i => !i.isEmpty)

        // generates a string of ASCII characters, with extra weighting for printable characters.
        // bad: generates characters that make the terminal beep.
        val asciiStr = Gen.asciiStr
        // generates a string of ASCII printable characters.
        // this is better, but it doesn’t generate many blanks.
        val asciiPrintableStr = Gen.asciiPrintableStr
        val simpleString: Gen[String] = Gen.asciiPrintableStr.suchThat(i => !i.isEmpty)

    }

    var count = 0

    // property("Q: basic") = forAll(GenString.genNonEmptyString) { (s: String) =>
    // property("Q: basic") = forAll(Gen.asciiPrintableStr) { (s: String) =>
    // property("Q: basic") = forAll { (s: String) =>
    property("Q: basic") = forAll(GenString.genAlphaStringsLength0To10) { (s: String) =>
        val rez = Q"""$s"""
        // ===== START DEBUG =====
        System.err.println(s"[$count] String = \"${s}\""); count = count + 1
        System.err.println(s"REZ == |$rez|")
        System.err.println("------------------------")
        // ===== STOP DEBUG =====
        if s.trim == "" then
            rez == List()
        else
            // these things should be true for any single non-empty string
            rez.length == 1 && rez.head == s
        end if
    }

}



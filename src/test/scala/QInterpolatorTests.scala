package com.alvinalexander.utils

import com.alvinalexander.utils.QInterpolator.Q
import org.scalatest.funsuite.AnyFunSuite

class QInterpolatorTests extends AnyFunSuite:

    test("`Q`: empty strings return empty lists") {
        val emptySeq = Seq[String]()

        // single-line
        var list = Q"";   assert(list == emptySeq)
        list = Q"  ";     assert(list == emptySeq)

        // multi-line
        list = Q"""
               """
        assert(list == emptySeq)

        list = Q"""

               """
        assert(list == emptySeq)
    }

    test("`Q` works on one-item list") {
        var list = Q"bar"
        assert(list == Vector("bar"))

        list = Q"""foo"""
        assert(list == Vector("foo"))

        list = Q"""
                foo
            """
        assert(list == Vector("foo"))

        list = Q"""foo
            """
        assert(list == Vector("foo"))

        list = Q"""
            foo"""
        assert(list == Vector("foo"))
    }

    test("`Q` removes blank spaces") {
        var list = Q" bar "
        assert(list == Vector("bar"))

        list = Q"  foo bar  "
        assert(list == Vector("foo bar"))

        list = Q"   baz   "
        assert(list == Vector("baz"))
    }

    test("`Q` works on multi-item list") {
        val list = Q"""
                apples
                bananas
                cherries
            """
        assert(list == Vector("apples", "bananas", "cherries"))
    }

    test("`Q` works on multiline string with multi-words per line") {
        val list = Q"""
                apples and
                bananas and
                cherries the end
            """
        assert(
            list == Vector(
                "apples and", 
                "bananas and", 
                "cherries the end"
            )
        )
    }

end QInterpolatorTests





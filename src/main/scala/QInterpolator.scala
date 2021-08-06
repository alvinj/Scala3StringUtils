package com.alvinalexander.utils

object QInterpolator:
    extension(sc: StringContext)
        def Q(args : Any*): Seq[String] =
            val strings = sc.parts.iterator
            val expressions = args.iterator
            var buf = new StringBuffer(strings.next)
            while strings.hasNext do
                buf.append(expressions.next)
                buf.append(strings.next)
            buf.toString.split("\n")
               .map(_.trim)
               .filter(_ != "")
               .toList
        end Q
    end extension
end QInterpolator

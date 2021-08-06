StringUtils
===========

This is my Scala “String Utilities” library, 
updated for Scala 3, and includes my “Q” interpolator.

`Q` converts a multiline string into a list of strings:

```scala
// input:
val xs = Q"""
a
b
c
"""

// result:
xs == Vector("a", "b", "c")
```

Please see the source code and tests for more
information.

Alvin Alexander    
https://alvinalexander.com


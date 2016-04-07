# hy-clj

This is a playground to embed [hy](https://github.com/hylang/hy) in
Clojure (and vice-versa). There is also
[LispSyntax](https://github.com/swadey/LispSyntax.jl) and many other
Lisps which are interesting.

## Motivation

Many people still think the Lisp syntax is "unnatural", while it is a
minimal syntactic hull to express von Neumann computing
(code=data). The minimum to express computation in this environment is
an ordered/sequential datastructure, i.e. a List. Why this minimalism
in syntax (independant of language semantics, e.g. imperative,
functional, ...) is so beneficial can be best shown by demonstrating
how easy it can be to integrate different modern Lisp environments.

I for example have done quite a bit of scientific computing and
machine learning in Python lately, and here Hy is a modern Lisp syntax
with extensions tailored to Python.

The Lisps need to agree on as much syntax as possible for this to be
seamless. Quotation is often different, so macros don't embed well.
But the idea is not to write big parts of code in the embedded
runtime, it is better to that in the native environment, but rather
foster experimentation with snippets.


## Usage

I still would like to call some
Python functions from time to time and hence have a written a simple
macro for now:

~~~clojure
(require '[hy-clj.core])

(-> (hy (import numpy)
        (import json)
        (.mean numpy [[1 2 3]
                      [4 5 6]]
               :axis 0))

   ;; edn/transit would be a reasonable default between Clojury lisps
   ;; JSON could be a reasonable baseline as it has fast parsers on most runtimes
   read-string) ;; => [2.5 3.5 4.5]
~~~

## TODO

- Demonstrate other Lisps and more examples. Actually R might also be
interesting, but I couldn't find a Lisp there.

## License

Copyright © 2016 Christian Weilbach

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

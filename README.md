# alda-core

The core machinery of Alda, implemented in Clojure.

## Components

* **alda.parser** (reads Alda code and transforms it into Clojure code in the context of the `alda.lisp` namespace)

* **alda.lisp** (a Clojure DSL which provides the context for evaluating an Alda score, in its Clojure code form)

* **alda.sound** (generates sound based on the data map produced by parsing and evaluating Alda code)

* **alda.now** (an entrypoint for using Alda as a Clojure library)

* **alda.repl** (an interactive **R**ead-**E**val-**P**lay **L**oop for Alda code)

## Development

See the [Alda development guide](https://github.com/alda-lang/alda/blob/master/doc/development-guide.md).

## License

Copyright © 2016 Dave Yarwood et al

Distributed under the Eclipse Public License version 1.0.

(set-env!
  :source-paths   #{"src" "test"}
  :resource-paths #{"grammar" "examples"}
  :dependencies   '[
                    ; dev
                    [adzerk/bootlaces "0.1.13" :scope "test"]
                    [adzerk/boot-test "1.0.4"  :scope "test"]

                    ; alda.core
                    [org.clojure/clojure    "1.8.0"]
                    [instaparse             "1.4.1"]
                    [io.aviso/pretty        "0.1.20"]
                    [com.taoensso/timbre    "4.1.1"]
                    [djy                    "0.1.4"]
                    [jline                  "2.12.1"]
                    [org.clojars.sidec/jsyn "16.7.3"]
                    [potemkin               "0.4.1"]
                    [clj_manifest           "0.2.0"]])

(require '[adzerk.bootlaces :refer :all]
         '[adzerk.boot-test :refer :all])

(def ^:const +version+ "0.0.1")

(bootlaces! +version+)

(task-options!
  pom     {:project 'alda/core
           :version +version+
           :description "The core machinery of Alda"
           :url "https://github.com/alda-lang/alda-core"
           :scm {:url "https://github.com/alda-lang/alda-core"}
           :license {"name" "Eclipse Public License"
                     "url" "http://www.eclipse.org/legal/epl-v10.html"}}

  jar     {:file "alda-core.jar"}

  install {:pom "alda/core"}

  target  {:dir #{"target"}}

  test    {:namespaces '#{; general tests
                          alda.parser.barlines-test
                          alda.parser.clj-exprs-test
                          alda.parser.event-sequences-test
                          alda.parser.comments-test
                          alda.parser.duration-test
                          alda.parser.events-test
                          alda.parser.octaves-test
                          alda.parser.repeats-test
                          alda.parser.score-test
                          alda.parser.variables-test
                          alda.lisp.attributes-test
                          alda.lisp.cram-test
                          alda.lisp.chords-test
                          alda.lisp.code-test
                          alda.lisp.duration-test
                          alda.lisp.global-attributes-test
                          alda.lisp.markers-test
                          alda.lisp.notes-test
                          alda.lisp.parts-test
                          alda.lisp.pitch-test
                          alda.lisp.score-test
                          alda.lisp.variables-test
                          alda.lisp.voices-test
                          alda.util-test

                          ; benchmarks / smoke tests
                          alda.examples-test}})

(deftask dev
  "Runs the Alda REPL for development."
  []
  (comp
    (with-pass-thru fs
      (require 'alda.repl)
      ((resolve 'alda.repl/start-repl!)))
    (wait)))

(deftask package
  "Builds jar file."
  []
  (comp (pom)
        (jar)))

(deftask deploy
  "Builds jar file, installs it to local Maven repo, and deploys it to Clojars."
  []
  (comp (package) (install) (push-release)))


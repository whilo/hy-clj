(ns hy-clj.core
  (:require [clojure.java.shell :as sh]
            [cheshire.core :as json]))

(defmacro hy
  "Embed hy snippets in Clojure code. Returns the value of the
  expression as string."
  [& code]
  (let [code# (pr-str (list 'print (conj code 'do)))]
    `(let [{out# :out err# :err exit# :exit} (sh/sh "hy" "-c" ~code#)]
       (when-not (zero? exit#)
         (throw (ex-info "Hy evaluation failed." {:code ~code# :error err#})))
       (try
         out#
         (catch Exception e#
           (throw (ex-info "Failed parsing output." {:code ~code# :output out# :error e#})))))))

(comment
  (-> (hy (import numpy)
          (import json)
          (.mean numpy [[1 2 3]
                        [4 5 6]]
                 :axis 0))
      read-string)

  (hy "foo"))

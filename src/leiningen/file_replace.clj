(ns leiningen.file-replace
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [leiningen.core.main :as main]))

(defn check-key
  [m v]
  (or ((keyword v) m) v))

(defn lookaround
  [behind ahead]
  (re-pattern (format "(?<=%s).*(?=%s)" behind ahead)))

(defn gsub
  [m s re v]
  (s/replace s (re-pattern re) (check-key m v)))

(defn file-replace
  "Replace pattern in file, possibly with key from project map.

The file path should be relative to the project's :root.

If the replacement text is the name of a key in the project map, e.g.
\"version\", its value will be used. Otherwise, the replacement text
will be used as is, possibly containing references to pattern groups
using $1, $2, etc"
  ([project path pattern replacement]
     (let [file (io/file (:root project) path)]
       (if (.exists file)
         (spit file (gsub project (slurp file) pattern replacement))
         (main/abort (str "Missing file: " file)))))
  ([project path lookbehind lookahead replacement]
     (file-replace project path (lookaround lookbehind lookahead) replacement)))

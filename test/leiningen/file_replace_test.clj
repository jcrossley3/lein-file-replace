(ns leiningen.file-replace-test
  (:require [clojure.test :refer :all]
            [leiningen.file-replace :refer :all]
            [clojure.string :as s]))

(deftest text-in-map
  (is (= 1 (check-key {:a 1} "a"))))

(deftest lookaround-should-work
  (let [re (lookaround "before" "after")]
    (is (= "now" (re-find re "beforenowafter")))
    (is (= "" (re-find re "beforeafter")))
    (is (nil? (re-find re "beforeandaft")))))

(deftest gsub-should-work
  (are [expect m s re v] (= expect (gsub m s re v))
       "foo 1.1"   {}          "foo 0.1"   #"\d.\d"                 "1.1"
       "foo 1.1"   {:v "9.9"}  "foo 0.1"   #"\d.\d"                 "1.1"
       "foo 1.1"   {:v "1.1"}  "foo 0.1"   #"\d.\d"                 "v"
       "x '1.1'"   {:v "1.1"}  "x '0.2'"   (lookaround "x '" "'")   "v"))

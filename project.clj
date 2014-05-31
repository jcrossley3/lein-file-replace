(defproject lein-file-replace "0.1.1-SNAPSHOT"
  :description "Text replacement in files using regexes"
  :url "http://github.com/jcrossley3/lein-file-replace"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true

  :plugins [[lein-file-replace "0.1.0"]]
  :deploy-repositories [["releases" {:url "https://clojars.org/repo/" :creds :gpg}]]
  :signing {:gpg-key "92439EF5"}
  :pom-location "target"

  :release-tasks
  [["vcs" "assert-committed"]
   ["change" "version" "leiningen.release/bump-version" "release"]

   ["file-replace" "README.md" "lein-file-replace \"" "\"]" "version"]

   ["vcs" "commit"]
   ["vcs" "tag"]
   ["deploy"]
   ["change" "version" "leiningen.release/bump-version"]
   ["vcs" "commit"]
   ["vcs" "push"]]  )

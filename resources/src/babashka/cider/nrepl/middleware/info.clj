(ns cider.nrepl.middleware.info
  "BB stand-in: upstream needs compliment, orchard.eldoc and orchard.java.
  Answers the info ops with babashka's own lookup."
  {:no-doc true}
  (:require
   [babashka.nrepl.impl.server :as server]
   [cider.nrepl.middleware.util.error-handling :refer [with-op-aliases with-safe-transport]]
   [clojure.string :as str]))

(defn- lookup [msg]
  (#'server/lookup-reply (update msg :op str/replace #"^cider/" "")))

(defn handle-info [handler msg]
  (with-safe-transport handler msg
    (with-op-aliases {"cider/info" lookup
                      "cider/eldoc" lookup})))

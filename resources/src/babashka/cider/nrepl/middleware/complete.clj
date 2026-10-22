(ns cider.nrepl.middleware.complete
  "BB stand-in: upstream completes with compliment. Answers with babashka's
  own completion."
  {:no-doc true}
  (:require
   [babashka.nrepl.impl.server :as server]
   [cider.nrepl.middleware.util.error-handling :refer [with-op-aliases with-safe-transport]]))

(defn handle-complete [handler msg]
  (with-safe-transport handler msg
    (with-op-aliases {"cider/complete" #(#'server/complete-reply %)})))

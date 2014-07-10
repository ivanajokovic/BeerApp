(ns beer.util.util
  (:require [noir.session :as session]
            [beer.views.views :as views]
			[beer.db.db :as db]
			[beer.service.service :as service]))


(defn process-login [username]
 (session/put! :username username)
 (views/home-after-login username))

(defn check-login [username password]
  (if (nil? (first (db/login username password)))
    (str "ERROR")
    (process-login username)
))



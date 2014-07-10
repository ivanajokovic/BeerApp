(ns beer.core
  (:require [clj-http.client :as client]
            [beer.db.db :as db]
            [beer.service.service :as service]
		        [beer.views.views :as views]
			      [beer.util.util :as util]
            [noir.session :as session]
            [noir.server :as server]
            [noir.core :as noir]
            [noir.response :as response]
            [clojure.java.io :as io]))


(def not-nil? (complement nil?))


(noir/defpage "/" {}
(session/clear!)
(if (not-nil?  (session/get :username))
	(response/redirect "/home")
  (views/home)))

  (noir/defpage "/home.html" {}
  (if (nil?  (session/get :username))
	(response/redirect "/")
  (views/home)))

(noir/defpage "/breweries.html" {}
  (views/breweries 1))

(noir/defpage [:post "/breweries.html"] {:keys [brewery-search]}
  (views/breweries brewery-search))


(noir/defpage [:post "/logout"] []
  (session/clear!)
  (response/redirect "/"))

(noir/defpage [:post "/register"] {:keys [firstname lastname username email password]}
  (db/register firstname lastname username email password)
  (views/home-after-register username)
  (response/redirect "/"))

(noir/defpage [:post "/"] {:keys [username password]}
  (util/check-login username password))

(noir/defpage [:post "/create-beer"] {:keys [beer-name beer-description beer-img]}
  (io/copy (io/file (:tempfile beer-img)) (io/file (str  (System/getProperty "user.dir") "/resources/public/images" (:filename beer-img) )))
  (db/insert-beer beer-name beer-description (:filename beer-img))
  (response/redirect "/"))

(noir/defpage "/beers.html" {}
  (views/beers 1))

(noir/defpage [:post "/beers.html"] {:keys [beer-search]}
    (views/beers beer-search))

(noir/defpage "/stores.html" {}
  (views/stores 2002))

(noir/defpage [:post "/stores.html"] {:keys [stores-search]}

  (views/stores stores-search))

(defn -main []
 (db/insert-dummy-data)
 (server/start 5949)
)

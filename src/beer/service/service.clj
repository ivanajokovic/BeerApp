(ns beer.service.service
  (:require [clj-http.client :as client]))



(defn find-breweries [id]
	 (:body(client/get (str "http://api.openbeerdatabase.com/v1/breweries/" id ".json")
                         {:as :json} ))
               )

(defn find-beer [id]
	 (:body(client/get (str "http://api.openbeerdatabase.com/v1/beers/" id ".json")
                         {:as :json} ))
               )

(defn find-stores [id]
	 (:body(client/get (str "http://ontariobeerapi.ca/stores/" id)
                         {:as :json} ))
               )



(defn find-products [id]
	 (:body(client/get (str "http://ontariobeerapi.ca/stores/" id "/products")
                         {:as :json} ))
               )

(defn find-products-onsale [id]
	 (:body(client/get (str "http://ontariobeerapi.ca/stores/" id "/products/?on_sale=true")
                         {:as :json} ))
               )

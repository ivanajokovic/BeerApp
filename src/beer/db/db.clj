(ns beer.db.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:refer-clojure :exclude [sort find])
  (:use monger.query))

(defn find-last-three-beers []
  (mg/connect!)
  (mg/set-db!(mg/get-db "beers"))
  (with-collection "beer"
    (find {})
    (fields [:name :image :description])
    (sort (sorted-map :_id -1))
    (limit 3)))

(defn insert-dummy-data []
  (mg/connect!)
  (mg/set-db!(mg/get-db "beers"))
  (mc/insert "beer" {:name "Lav", :description "Lav Pivo is a popular Serbian beer brand.
Produced and bottled by Carlsberg Srbija in the village of Čelarevo, (Bačka Palanka municipality)
it has the second biggest market share among the beer brands in Serbia, behind their rivals Jelen Pivo.
In addition to Serbia, it is also widespread in Montenegro and Bosnia-Herzegovina.
Lav Pivo is the official beer of the Serbian national football team and also the Serbian Cup
in football, which bears the brand's name (since 2006 it is known as the Lav Cup).",
:image "images/lav.jpg"})
	  (mc/insert "beer" {:name "Jelen", :description "Jelen Pivo is a pale lager produced by the
Apatin Brewery from Serbia. Jelen Pivo contains 5% alcohol,
belongs to the class of light lagers. Jelen Pivo has won various awards within Serbia
and participates in a variety of sponsorships. Its logo is a bugling red deer.", :image "images/jelen.jpg"})
	  (mc/insert "beer" {:name "Niksicko", :description "Nikšićko Pivo, brewed with natural
ingredients and pure mountain water, is the number one quality beer in the former Yugoslavia
and the only brewery in Montenegro. The company produces four lagers under the brand name Nik:
ikšićko Pivo, Nik Gold, Nik Cool, and Nikšićko Tamno.
Besides Nikšićko beer, Beck's beer is also bottled in the Trebjesa brewery,
and is sold as a domestic Montenegrin beer.", :image "images/niksicko.jpg"})
  (mc/insert "users" {:firstname "Ivana", :lastname "Jokovic", :username "admin", :email "ivana@ivana.com", :password "admin"}))

(defn login [username password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "beers"))
  (mc/find-maps "users" {:username username, :password password}))

(defn register [firstname lastname username email password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "beers"))
  (mc/insert "users" {:firstname firstname, :lastname lastname, :username username, :email email, :password password}))

(defn insert-beer [beer-name beer-description beer-image]
  (mg/connect!)
  (mg/set-db!(mg/get-db "beers"))
  (mc/insert "beer" {:name beer-name, :description beer-description, :image beer-image}))

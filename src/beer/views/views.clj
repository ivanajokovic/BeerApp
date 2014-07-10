(ns beer.views.views
  (:require [net.cgrand.enlive-html :as html]
    		[noir.session :as session]
			[beer.db.db :as db]
			[beer.service.service :as service]
    ))

(def not-nil? (complement nil?))

(html/deftemplate home "beer/views/home.html"
  []
  [:#img1] (html/set-attr :src (:image (nth (db/find-last-three-beers) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-last-three-beers) 1)))
  [:#img3] (html/set-attr :src (:image (nth (db/find-last-three-beers) 2)))

  [:#name1] (html/content (:name (nth (db/find-last-three-beers) 0)))
  [:#name2] (html/content (:name (nth (db/find-last-three-beers) 1)))
  [:#name3] (html/content (:name (nth (db/find-last-three-beers) 2)))

  [:#description1] (html/content (:description (nth (db/find-last-three-beers) 0)))
  [:#description2] (html/content (:description (nth (db/find-last-three-beers) 1)))
  [:#description3] (html/content (:description (nth (db/find-last-three-beers) 2)))

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))

  )

(html/deftemplate breweries "beer/views/breweries.html" [brewery]
  [:#name] (html/content (:name (service/find-breweries brewery)) )
  [:#url] (html/content (:url (service/find-breweries brewery)) )
  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))

  )

(html/deftemplate home-after-login "beer/views/home.html"
  [username]
  [:#welcomeMsg] (html/content (str "Welcome " username "!"))
  [:#loginBtn] (html/remove-class "login-visible")
  [:#loginBtn] (html/add-class "login-not-visible")
  [:#logoutBtn] (html/remove-class "login-not-visible")
  [:#logoutBtn] (html/add-class "login-visible")

  [:#img1] (html/set-attr :src (:image (nth (db/find-last-three-beers) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-last-three-beers) 1)))
  [:#img3] (html/set-attr :src (:image (nth (db/find-last-three-beers) 2)))

  [:#name1] (html/content (:name (nth (db/find-last-three-beers) 0)))
  [:#name2] (html/content (:name (nth (db/find-last-three-beers) 1)))
  [:#name3] (html/content (:name (nth (db/find-last-three-beers) 2)))

  [:#description1] (html/content (:description (nth (db/find-last-three-beers) 0)))
  [:#description2] (html/content (:description (nth (db/find-last-three-beers) 1)))
  [:#description3] (html/content (:description (nth (db/find-last-three-beers) 2)))
  )

(html/deftemplate home-after-register "beer/views/home.html"
  [username]
  [:#welcomeMsg] (html/content (str "Welcome " username ". You are successfully registered."))
  [:#loginBtn] (html/remove-class "login-visible")
  [:#loginBtn] (html/add-class "login-not-visible")
  [:#logoutBtn] (html/remove-class "login-not-visible")
  [:#logoutBtn] (html/add-class "login-visible")

  [:#img1] (html/set-attr :src (:image (nth (db/find-last-three-beers) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-last-three-beers) 1)))
  [:#img3] (html/set-attr :src (:image (nth (db/find-last-three-beers) 2)))

  [:#name1] (html/content (:name (nth (db/find-last-three-beers) 0)))
  [:#name2] (html/content (:name (nth (db/find-last-three-beers) 1)))
  [:#name3] (html/content (:name (nth (db/find-last-three-beers) 2)))

  [:#description1] (html/content (:description (nth (db/find-last-three-beers) 0)))
  [:#description2] (html/content (:description (nth (db/find-last-three-beers) 1)))
  [:#description3] (html/content (:description (nth (db/find-last-three-beers) 2))))

(html/deftemplate beers "beer/views/beers.html" [beer]
  [:#createBeer] (if (nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn"))
  [:#createBeer] (if (not-nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn"))

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))

  [:#name] (html/content (:name (service/find-beer beer)) )
  [:#beer-description] (html/content (:description (service/find-beer beer)) )

  )

(html/deftemplate stores "beer/views/stores.html" [store]
  []
   (def product (service/find-products-onsale store))

  [:#name] (html/content (:name (service/find-stores store)) )
  [:#address] (html/content (:address (service/find-stores store)) )
  [:#city] (html/content (:city (service/find-stores store)) )
  [:#postal_code] (html/content (:postal_code (service/find-stores store)) )
  [:#phone] (html/content (:phone (service/find-stores store)) )
  ;[:#products] (html/content (service/find-products store))
  ;[:#products] (html/content (service/find-products-onsale store))

  [:#product_name1] (html/content (:name (nth product 0)) )
  [:#price1] (html/content (:price (nth product 0)) )
  [:#image1] (html/set-attr :src(:image_url (nth product 0)) )
  [:#abv1] (html/content (:abv (nth product 0)) )
  [:#type1] (html/content (:type (nth product 0)) )
  [:#brewer1] (html/content (:brewer (nth product 0)) )
  [:#country1] (html/content (:country (nth product 0)) )

  [:#product_name2] (html/content (:name (nth product 1)) )
  [:#price2] (html/content (:price (nth product 1)) )
  [:#image2] (html/set-attr :src(:image_url (nth product 1)) )
  [:#abv2] (html/content (:abv (nth product 1)) )
  [:#type2] (html/content (:type (nth product 1)) )
  [:#brewer2] (html/content (:brewer (nth product 1)) )
  [:#country2] (html/content (:country (nth product 1)) )

  [:#product_name3] (html/content (:name (nth product 2)) )
  [:#price3] (html/content (:price (nth product 2)) )
  [:#image3] (html/set-attr :src(:image_url (nth product 2)) )
  [:#abv3] (html/content (:abv (nth product 2)) )
  [:#type3] (html/content (:type (nth product 2)) )
  [:#brewer3] (html/content (:brewer (nth product 2)) )
  [:#country3] (html/content (:country (nth product 2)) )


  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))

  )



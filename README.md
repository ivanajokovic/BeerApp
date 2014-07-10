# beer

A Clojure web application for listing beers, breweries, stores and products on sale at certain store. The application is written in Clojure and uses the following libraries: Noir, Enlive and Monger libraries.
This application is designed for learning Clojure.

The application calls a REST service from http://api.openbeerdatabase.com and displays beers and breweries, and calls a REST service from http://ontariobeerapi.ca/ and displays stores with their products.
First the database is filled with some dummy data about beers(name, description and image). After that user can see these beers on home page.

When web application is started, initial user with credentials:
 - username: admin
 - password admin,

is imported into the database. User can register and log in. After logging in, user is redirected to the Home page, and he is able to add new beer into the database. Adding beers is enabled on the beers page.
User can add name, description and upload a image about the beer that will be displayed on the home page.

 
The application consists of 4 pages:

1. Home page - serves as a starting point and displays top three beers from database and their information.
2. Beers page - displays one default beer (id = 1, name ="(512) ALT") and a search bar where user can search beers via REST service. User can search for a bear using only beer id.
3. Breweries page - displays one default brewery (id = 1, name = "(512) Brewing Company") and a search bar where user can search breweries via REST service. User can search for a brewery using only brewery id.
4. Stores - displays onde default store (id = 2002, name = "Queen St.") and a search bar where user can search stores via the second REST service, and the user can see 3 product that are curently on sale at that store. 
User can search for a store using only store id.

## Usage

Download and install [Leiningen](https://github.com/technomancy/leiningen), and then download and install [MongoDB](http://www.mongodb.org/downloads).
It's necessary to start MongoDB before running the application. Database used in this project is MongoDB 2.6. To start database open command line, navigate to mongodb/bin folder,
and then execute mongod.exe (on windows). For more detailed instructions on how to start MongoDB, see http://docs.mongodb.org/manual/installation/.

The application automaticaly inserts dummy data in database. If you would like to prevent this comment out the first line in the main method (db/insert-dummy-data).

To start the application navigate to the root folder of "beer" application and enter: lein run.

## References

[Practical Clojure](http://www.amazon.com/Practical-Clojure-Experts-Voice-Source/dp/1430272317), Luke VanderHart and Stuart Sierra, 
[Clojure Programming](http://www.amazon.com/Clojure-Programming-Chas-Emerick/dp/1449394701), Chas Emerick, Brian Carper and Chrisophe Grand and
Web Development with Clojure, Build Bulletproof Web Apps with Less Code, Dmitri Sotnikov

## License

Distributed under the Eclipse Public License, the same as Clojure.

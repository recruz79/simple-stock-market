# Simple Stock Market
Single spring-boot project simulating an stock market application which allows operations like buy/sell stocks, calculate P/E ratio, Dividend Yield and All share index

#### Endpoints :

- POST http:\\localhost:8080\trade\buy : Creates a buy trade order.

- POST http:\\localhost:8080\trade\sell : Creates a sell trade order.

- GET  http:\\localhost:8080\stockPrice : Returns the price calculation of a stock, requires the stock name.

- GET  http:\\localhost:8080\peRatio : Returns the PE ratio of certain stock, requires the stock name and the price.

- GET  http:\\localhost:8080\marketAllShareIndex : Returns all the existing loans in the database. 

#### Validations:

- Usually all the non expected exceptions are catch by the ExceptionHandler and translated to 500 error.

- The expected exceptions are marked as MarketException and returned according the situation.

#### Tech Stack

- Java 8, Maven 3, Spring Boot.


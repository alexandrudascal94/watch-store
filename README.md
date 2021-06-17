# Watch Store API

## Overview

A single endpoint API service that take a list of watches and return the total cost. In terms of programming language Java 11 is
used. Below is a catalogue of four watches, and their associated prices, discounts:

Watch Id     | Watch Name  | Watch Price  | Watch Discount |  
------------- | ------------- | ------------- | ------------- |
001         | Rolex         | 100 | 3 for 200  |
002         | Michael Kors | 80  | 2 for 120  |
003         | Swatch        | 50 |   |
004         | Casio         | 30  |   |

### Requirements:
* The first two products have a possible discount. As an example, if the user attempts to
checkout three or six Rolex watches then they will receive the discount price once or twice,
respectively.
* There is no limit to the number of items or combinations of watches a user can checkout.
* There is no limit to the number of times a discount can be used.
* Similarly, a user can checkout a single item if they wish.

## Implementation 

The solution is implemented following TDD approach(see commits history) with functional and unit tests.

### To improve
The solution provides just one discount for a selling item, it can be enhanced and add multiple discounts (e.g. 3 items for 200, 5 items for 300) 

### Technologies used: 
 * Java 11
 * Spring Boot 
 * Junit 5
 * JPA (with H2 database)

### 
    
### API documentation

```json

Request:

POST http://localhost:8080/checkout
        
 Headers
Accept: application/json
Content-Type: application/json

 Body
[ 1, 2, 1, 4, 3]

Response: 
        
 Headers
Content-Type: app
        
 Body
{ 
  "price": 360 
}
```

### Build and Run

#### Requirements

    * Java 11
    * Maven 3+

#### Build


clone the git repository or downland the zip file and unzip

```
https://github.com/alexandrudascal94/watch-store.git
```

To build the application run command:

```
mvn clean install
```

#### Run
Then run the application with command: 

```
java -jar target/watch-store-1.0.0.jar
```

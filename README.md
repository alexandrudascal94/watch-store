# Watch Store API

## Overview

A single endpoint that take a list of watches and return the total cost. In terms of programming language Java 11 is
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

### Technologies used: 
 * Java 11
 * Spring Boot 
 * Junit 5
 * JPA (with H2 database)
    
### API documentation

```json

Request:

POST http://localhost:8080/checkout
        
# Headers
Accept: application/json
Content-Type: application/json

# Body
[
"001",
"002",
"001",
"004",
"003"
]

Response: 
        
# Headers
Content-Type: app
        
# Body
{ 
  "price": 360 
}
```

### Build and Run

#### Requiremts

    * Java 11
    * Maven 3+

#### Build
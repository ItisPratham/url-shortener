# URL Shortener Service

A simple URL shortener built with **Spring Boot**, **JPA**, and **Base62 encoding**.

## Features
- Convert long URLs into short Base62 strings
- Store and retrieve URLs from a database
- Unique short URLs based on database ID
- Simple design, not yet fully scalable

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Lombok
- Relational Database (MySQL)

## How It Works
1. Save the long URL to the database
2. Use the generated ID
3. Convert the ID to Base62
4. Pad the result to a fixed length
5. Store and return the short URL

## Example
```

Long URL  → [https://example.com/some/very/long/url]
Short URL → 000aZ

````

## Run the Project
```bash
mvn spring-boot:run
````

## API (Example)

* **POST** `/` – Create short URL
* **GET** `/{url}` – Redirect to original URL

## Notes

* Short URLs are generated using Base62
* `short_url` is nullable during creation and updated after ID generation

## Future Enhancements

* Make analytic service.
* Need to add frontend as well
* Make design scalable and deploy it on AWS
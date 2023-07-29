# ViennaCallingApp backend

`ViennaCallingApp backend` is a lightweight `Spring Boot` application written in `Java`. It provides backend APIs for the ViennaCallingApp application.  
It comes with multiple models and example output.

## Developing

This project is set up using `Maven` and uses its lifecycle actions.

1. Import this project as `Maven` project in your desired IDE.
2. Install dependencies via `mvn install`.
3. Run the main class - `ViennaCallingAppBackendApplication`.

### Front-end

It is possible to start the back-end server locally via `Docker` and expose it via a custom port and then use it in the front-end.

E.g. `docker run -p 8080:8080 jajcoszek/viennacalling-backend:latest` will enable the back-end server on local port 8080.

## Contributing

Please refer to our LICENSE and CONTRIBUTING.md for our guidelines.

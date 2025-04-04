# Card Game Project

## Requirements
- **JDK 21** (Ensure Java 21 is installed and registered in the PATH variable)
- **Maven 3.9.9** (Ensure Maven version 3.9.9 is installed and registered in the PATH variable)

## Build
To create the "all-in-one" JAR file, use:

mvn clean package

The generated `card-game.jar` will be located in the `target` directory.

## Run Help
To display usage instructions, run:

java -jar target/card-game.jar --help

## Run Random Deck
To execute the application with a randomly generated deck, use:

java -jar target/card-game.jar

## Run Custom Deck
To run the application with a custom deck configuration, specify the file

java -jar target/card-game.jar --file [custom configuration file path

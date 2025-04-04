
# Card Game Docker Image

## Build
docker build -f Dockerfile.build -t card-game-image .

## Run Help
docker run card-game-image --help

## Run Random Deck
docker run card-game-image    

## Run with Custom Deck (Windows)
1. Create a folder named `decks`.
2. Place your deck configuration file inside the folder, e.g., `wrong-format.txt`.
3. Run the container with the custom deck:

docker run -v .\decks:/app/decks card-game-image --file ./decks/wrong-format.txt

## Run with Custom Deck (Unix/macOS/Linux)
1. Create a folder named `decks`.
2. Place your deck configuration file inside the folder, e.g., `wrong-format.txt`.
3. Run the container with the custom deck:

docker run -v ./decks:/app/decks card-game-image --file ./decks/wrong-format.txtmvn
<!DOCTYPE html>
<html lang="en">
    <head></head>
    <body>
        <h1>Index</h1>
        <p>This api is built on spark web framework and Gson. The sqlite file should be located
        at the project root. The MVC model is adopted, the models represents objects/entities
        in the database(Album and Artist) in this case. The controllers are responsible for
        routes that are related to the models(get album by id, get artist by id/name). The Services
        are responsible for making the actual database calls and returns the data to its related
        controllers. The controllers will then return the json using Gson libraries</p>
        <p>Enter the path in the address bar to test the api calls, for example,
        "localhost:4567/album/2", "localhost:4567/artist/55", "localhost:4567/artist/Santana"</p>
        <p>Exception handled:</p>
        <ul>
            <li>Non-existing AlbumId</li>
            <li>Non-existing ArtistId</li>
            <li>If the artist entered doesn't have any albums, the count will be 0</li>
        </ul>
    </body>
</html>
window.onload = function() {
    var xhttp1 = xhttp("GET", "http://localhost:8080/SuggMovies-war/webresources/v1.0/indications", function() {
        var json = JSON.parse(xhttp1.responseText);
        console.log(json)
    });
};
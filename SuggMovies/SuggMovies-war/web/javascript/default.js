function xhttp (url, metodo, retorno) {
    var xhttp = new XMLHttpRequest();
    
    xhttp.open(metodo, url, false);
    xhttp.onreadystatechange = retorno;
    xhttp.send();
    
    return xhttp;
}
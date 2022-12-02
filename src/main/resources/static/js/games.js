var page = document.currentScript.getAttribute('page');
console.log(page);
function showInput(){
    var btnfilter = document.getElementById("btnfilter");
    btnfilter.disabled = false;
}

function filterGames(){
     var selectValue = document.getElementById("select_filter").value;
     console.log("Value select");
     console.log(selectValue)

    var inputValue = document.getElementById("input_filter").value;
    console.log("Value input");
    console.log(inputValue);

    window.location.href = "games?page=1&key=" + selectValue + "&value=" + inputValue;
}

function goGame(id){
    console.log(id);
      window.location.href = "game?id=" + id;
}
function deleteGame(id){
    var params = JSON.stringify(id);
    var http = new XMLHttpRequest();
    http.open('DELETE', 'game?id=' + id, true);

    http.setRequestHeader('Access-Control-Allow-Origin', '*');
    http.setRequestHeader('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, HEAD, OPTIONS');
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader('Access-Control-Allow-Headers', 'Content-Type, key,Access-Control-Allow-Headers,Access-Control-Allow-Origin, X-Requested-With');

    http.send();
    window.location.href = "../index";
}

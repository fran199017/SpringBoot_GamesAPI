var page = document.currentScript.getAttribute('page');
var gamescriteria = document.currentScript.getAttribute('gamescriteria');
console.log("gamesCriteria");
console.log(gamescriteria);
let arrayKeys = [];

console.log(page);
function showInput(){
    var btnfilter = document.getElementById("btnfilter");
    btnfilter.disabled = false;
}

function filterGames(){
    console.log("Formulario go")

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "games");
    xhr.send(gamesCriteria);
}

function isCheckedName(){
    if (document.getElementById('check_name').checked) {
        var inputName = document.getElementById('input_name').disabled = false;
        arrayKeys.push("name")
    } else {
        document.getElementById('input_name').disabled = true;
        var index = array.indexOf("name");
        if (index !== -1) {
          array.splice(index, 1);
        }
    }
    console.log(arrayKeys);
}

function isCheckedRating(){
    if (document.getElementById('check_rating').checked) {
        arrayKeys.push("rating")
        document.getElementById('input_rating').disabled = false;
    } else {
        var index = array.indexOf("rating");
        if (index !== -1) {
          array.splice(index, 1);
        }
        document.getElementById('input_rating').disabled = true;
    }
    console.log(arrayKeys);
}

function isCheckedYear(){
    if (document.getElementById('check_year').checked) {
        arrayKeys.push("year")
        document.getElementById('input_year').disabled = false;
    } else {
        var index = array.indexOf("rating");
        if (index !== -1) {
            array.splice(index, 1);
        }
        document.getElementById('input_year').disabled = true;
    }
     console.log(arrayKeys);
}

class GamesCriteria{
    constructor(keys,value,operator){
        this.key= keys;
        this.value = value;
    }
}

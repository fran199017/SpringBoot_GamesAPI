var page = document.currentScript.getAttribute('page');
let arrayKeys = [];

console.log(page);
function showInput(){
    var btnfilter = document.getElementById("btnfilter");
    btnfilter.disabled = false;
}

function filterGames(){
    var selectValue = document.getElementById("select_filter").value;
    var inputValue = document.getElementById("input_filter").value;

    window.location.href = "games?page=1&key=" + selectValue + "&value=" + inputValue;
}

function isCheckedName(){
    if (document.getElementById('check_name').checked) {
        var inputName = document.getElementById('input_name').disabled = false;
        arrayKeys.add("name")
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
        arrayKeys.add("rating")
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
        arrayKeys.add("year")
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

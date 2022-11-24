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

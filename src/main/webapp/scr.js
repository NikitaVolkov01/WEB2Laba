let svg = document.querySelector("svg");
svg.addEventListener("click", (event)=>{

    let r = Number(document.getElementById('r-input').value);
    let x = (event.offsetX - 225)/150 * r;
    let y = -(event.offsetY - 225)/150 * r;

    sendPoint("graph",x, y, r);

    if ( ((x * x + y * y) <= r/2 * r/2 && x >= 0 && y >= 0) || ( x <= 0 && y >= 0 && ((y -r)<=x + x))  || (x<=0 && y<=0 && x>=-r && y>=-r/2 ) ){
        textCloud.innerText = "В фигуру попали";
    }else{
        textCloud.innerText = "В фигуру не попали"
    }
    svg.innerHTML += "<circle r=\"4\" cx=" + event.offsetX + " cy=" + event.offsetY + " fill=#f2c8aa></circle>";

});

let mainForm = document.querySelector("form");
let clearButton = document.getElementById("clearButton");
let showHideButton = document.getElementById("showHideButton");
let textCloud = document.getElementById("textCloud");
let answerBody = document.getElementById("answerBody");

mainForm.addEventListener("submit", (event) => {
    event.preventDefault();
    let x = Number(document.getElementById('x-input').value);

    let y = document.querySelector("#wrapperY input").value;
    let afterDot = (y.toString().includes('.')) ? (y.toString().split('.').pop().length) : (0);
    if(isFinite(y) && afterDot>6){
        textCloud.innerText = "Не вводите больше 6 \nцифр после запятой";
    }else {
        if (isFinite(y)) {
            if (y >= -5 && y <= 5){
                if (y != "") {
                    y = Number(y).toFixed(6);
                    let r = Number(document.getElementById('r-input').value);

                    if ( ((x * x + y * y) <= r/2 * r/2 && x <= 0 && y <= 0) || (y > (x - r) && x >= 0 && y <= 0) || (x<=0 && y>=0 && x>=-r && y<=r ) ){
                        textCloud.innerText = "В фигуру попали";
                    } else {
                        textCloud.innerText = "В фигуру не попали";
                    }

                    svg.innerHTML += "<circle r=\"4\" cx=" + (x*150/r + 225) + " cy=" + (-y*150/r + 225) + " fill=#f2c8aa></circle>";
                    sendPoint("form", x, y, r);

                } else {
                    textCloud.innerText = "Введите Y, пожалуйста";
                }
            } else {
                textCloud.innerText = "Y должен быть в пределах (-5;5)";
            }

        } else {
            textCloud.innerText = "Y должен быть числом"
        }
    }
});

function sendPoint(key, x, y, r){

    //отправляет данные на сервер

    let requestBody = "?key=" + encodeURIComponent(key);
    requestBody += "&x=" + encodeURIComponent(x);
    requestBody += "&y=" + encodeURIComponent(y);
    requestBody += "&r=" + encodeURIComponent(r);
    fetch(requestBody, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        },
    }).then(response => response.text())
        .then(res => {
            answerBody.innerHTML = res;
            showHideButton.innerText = "Скрыть";
            toggleHide = true;
        });
}
clearButton.addEventListener('click', event => {
    event.preventDefault();
    fetch("", {
        method: 'HEAD',
    }).then(() => {
        answerBody.innerHTML = "";
        textCloud.innerText = "Таблица очищена";
        let circles = document.querySelectorAll("circle");
        for (let i=0; i<circles.length; i++){
            circles[i].parentNode.removeChild(circles[i]);
        }
    });
});

let toggleHide = false;
showHideButton.addEventListener('click', event => {
    event.preventDefault();
    fetch("?key=show", {
        method: 'GET',
    }).then(response => response.text())
        .then(res => {
            if (!toggleHide){
                showHideButton.innerText = "Скрыть";
                toggleHide = true;
                if (res !== "") {
                    answerBody.innerHTML = res;
                    textCloud.innerText = "Таблица показана";
                } else textCloud.innerText = "В таблице еще ничего нет";
                let rows = document.querySelectorAll("#answerBody tr");
                for (let i=0; i<rows.length; i++){
                    let tds = rows[i].children;
                    svg.innerHTML += "<circle r=\"4\" cx=" + (tds[2].innerText*150/tds[4].innerText + 225) + " cy=" + (-tds[3].innerText*150/tds[4].innerText + 225) + " fill=#f2c8aa></circle>";
                }
            }else {
                answerBody.innerHTML = "";
                showHideButton.innerText = "Показать таблицу";
                toggleHide = false;
                textCloud.innerText = "Таблица скрыта";
            }
        });
});
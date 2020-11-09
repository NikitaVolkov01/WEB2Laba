<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="ss.css">
    <title>web2</title>
</head>
<body background="0738.jpg" class="container">
<header class="header"><span>Волков Никита <br> P3230 Вариант 2611</span></header>
<div id="mainWrapper">
    <div id="svgWrapper" class="sidebar">
        <svg width="450" height="450">
            <polygon points="150,225 225,225 225,75"
                     fill="rgb(134, 125, 240)" fill-opacity="0.3" stroke="rgb(138, 202, 207)"></polygon>


            <polygon points="225,225 75,225 75,300, 225,300"
                     fill="rgb(183, 177, 240)" fill-opacity="0.3" stroke="rgb(138, 202, 207)"></polygon>


            <path d="M 300 225 A 75 75, 90, 0, 0, 225 150 L 225 225 Z"
                  fill="rgb(218, 214, 255)" fill-opacity="0.3" stroke="rgb(138, 202, 207)"></path>


            <line class="axis" x1="0" x2="450" y1="225" y2="225" stroke="rgb(177, 227, 221)" stroke-width="2"></line>
            <line class="axis" x1="225" x2="225" y1="0" y2="450" stroke="rgb(177, 227, 221)" stroke-width="2"></line>


            <polygon points="225,0 219,16 231,16" stroke="rgb(177, 227, 221)" stroke-width="2" fill="rgb(26, 66, 62)"></polygon>
            <polygon points="450,225 434,231 434,219" stroke="rgb(177, 227, 221)" stroke-width="2" fill="rgb(26, 66, 62)"></polygon>


            <line class="core-line" x1="300" x2="300" y1="230" y2="220" stroke="rgb(177, 227, 221)" stroke-width="2"></line>
            <line class="core-line" x1="375" x2="375" y1="230" y2="220" stroke="rgb(177, 227, 221)" stroke-width="2"></line>

            <line class="core-line" x1="75" x2="75" y1="230" y2="220" stroke="rgb(177, 227, 221)" stroke-width="2"></line>
            <line class="core-line" x1="150" x2="150" y1="230" y2="220" stroke="rgb(177, 227, 221)" stroke-width="2"></line>

            <line class="core-line" x1="220" x2="230" y1="150" y2="150" stroke="rgb(177, 227, 221)" stroke-width="2"></line>
            <line class="core-line" x1="220" x2="230" y1="75" y2="75" stroke="rgb(177, 227, 221)" stroke-width="2"></line>

            <line class="core-line" x1="220" x2="230" y1="300" y2="300" stroke="rgb(177, 227, 221)" stroke-width="2"></line>
            <line class="core-line" x1="220" x2="230" y1="375" y2="375" stroke="rgb(177, 227, 221)" stroke-width="2"></line>


            <text class="core-text" x="290" y="215">R/2</text>
            <text class="core-text" x="370" y="215">R</text>

            <text class="core-text" x="65" y="215" >-R</text>
            <text class="core-text" x="135" y="215">-R/2</text>

            <text class="core-text" x="235" y="155">R/2</text>
            <text class="core-text" x="235" y="80">R</text>

            <text class="core-text" x="235" y="305">-R/2</text>
            <text class="core-text" x="235" y="379">-R</text>

        </svg>
    </div>



    <div id="formWrapper" class="content">
        <form method="get" action="">

            <div id="wrapperX">
                <input id="x_input" class="form_input" placeholder="X" disabled>
                <label for="x_input"></label>
                <select id = "x-input" name = "x">
                    <option value="-5">-5</option>
                    <option value="-4">-4</option>
                    <option value="-3">-3</option>
                    <option value="-2">-2</option>
                    <option value="-1">-1</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <br>
            <br>

            <div id="wrapperY">
                <input type = "text" name ="y" placeholder="Y (-5; 5)" maxlength="10">
                <br>
            </div>


            <div id="wrapperR">
                <input id="r_input" class="form_input" placeholder="R" disabled >
                <label for="r_input"></label>
                <select id = "r-input" name = "r">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>


            <br>
            <br>
            <div id="sendClearWrapper" class="knopka">
                <button>Отправить</button>
                <button id="clearButton">Очистить</button>
                <button id="showHideButton">Показать таблицу</button>
            </div>

        </form>
    </div>
</div>

<div id="answer">
    <p id="textCloud" class ="textCloud" > Приветики! </p>
</div>

<table class="footer">
    <tr>
        <th>Дата и время запроса</th>
        <th>Время выполнения</th>
        <th>Координата X</th>
        <th>Координата Y</th>
        <th>Параметр R</th>
        <th>Результат</th>
    </tr>
    <tbody id="answerBody"></tbody>
</table>
</body>
<script src="scr.js"></script>
</html>
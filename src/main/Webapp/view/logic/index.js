const pointsByR = {};

function sendCoordinates(event) {
    event.preventDefault();
    const svg = document.getElementById("graph");
    const rect = svg.getBoundingClientRect();

    let x = ((event.clientX - rect.left - 250) / 200).toFixed(6);
    let y = ((250 - (event.clientY - rect.top)) / 200).toFixed(6);

    let rElement = document.getElementById("form:r");
    let r = rElement.value;
    x = x*r;
    y = y*r;

    document.getElementById("graphForm:hit").value = "";

    document.getElementById("graphForm:xCoord").value = x;
    document.getElementById("graphForm:yCoord").value = y;
    document.getElementById("graphForm:rValue").value = r;

    if (isNaN(x) || isNaN(y) || isNaN(r)){
        return;
    }
    document.getElementById("graphForm:graphFormButton").click();
    const interval = setInterval(() => {
        const hit = document.getElementById("graphForm:hit").value;

        if (hit) {
            clearInterval(interval);
            console.log(`Hit received: ${hit}`);
            console.log(x + ' ' + y + " " + r)

            if (!pointsByR[r]) {
                pointsByR[r] = [];
            }

            const selectedR = document.getElementById("form:r").value;
            if (selectedR) {
                console.log("Rendering points for r =" + selectedR);
                renderPoints(selectedR);
            }

            pointsByR[r].push({ x, y, hit });
            drawPoint(x, y, r, hit);

        }
    }, 100);

}

function drawPointfromStandart(){
    const interval = setInterval(() => {
        x = document.getElementById("form:x").value;
        y = document.getElementById("form:y").value;
        r = document.getElementById("form:r").value;

        const hit = document.getElementById("graphForm:hit").value;

        if (hit) {
            clearInterval(interval);
            console.log(`Я ПОПАЛА СЮДААААААААА`);
            console.log(x + ' ' + y + " " + r)

            if (!pointsByR[r]) {
                pointsByR[r] = [];
            }

            if (r) {
                console.log("Rendering points for r =" + r);
                renderPoints(r);
            }

            pointsByR[r].push({ x, y, hit });
            drawPoint(x, y, r, hit);

        }
    }, 100);
}



function drawPoint(xCoord, yCoord, r, h) {
    if (isNaN(xCoord) || isNaN(yCoord) || isNaN(r)){
        return;
    }
    console.log("drawing " + xCoord + " " + yCoord + " " + r + " hit " + h)
    const svg = document.getElementById("graph");
    const point = document.createElementNS("http://www.w3.org/2000/svg", "circle");

    const svgX = 250 + (xCoord/r  * 200);
    const svgY = 250 - (yCoord/r  * 200);

    point.setAttribute("cx", svgX);
    point.setAttribute("cy", svgY);
    point.setAttribute("r", 5);
    point.setAttribute("fill", h == "true" ? "green" : "red");
    point.classList.add("graph-point");
    svg.appendChild(point);
}

function clearPoints() {
    const svg = document.getElementById("graph");
    const points = svg.querySelectorAll('.graph-point');
    points.forEach(point => point.remove());
}
function cclear() {
    console.log("clear is invoked");
    rs = [1,2,3,4,5]
    rs.forEach(value =>{
            delete pointsByR[value];
        }
    )
    clearPoints();

}

window.addEventListener("beforeunload", () => {
    localStorage.setItem("pointsByR", JSON.stringify(pointsByR));
    console.log("Saved pointsByR to localStorage", pointsByR);
});


document.addEventListener("DOMContentLoaded", () => {
    const savedPoints = localStorage.getItem("pointsByR");
    if (savedPoints) {
        console.log("Restoring pointsByR from localStorage...");
        const restoredPoints = JSON.parse(savedPoints);
        console.log("Restored pointsByR:", restoredPoints);
        Object.assign(pointsByR, restoredPoints);
    }

    // Обновляем pointsByR из таблицы
    const table = document.getElementById("results");
    const rows = table.getElementsByTagName("tr");

    console.log("Clearing current pointsByR before populating from table...");
    Object.keys(pointsByR).forEach(r => {
        pointsByR[r] = [];
    });

    // Читаем данные из таблицы
    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName("td");

        if (cells.length < 4) {
            console.warn("Invalid row format, skipping...");
            continue;
        }

        const hit = cells[0].innerText.trim() === "Попадание" ? "true" : "false";
        const x = parseFloat(cells[1].innerText.trim());
        const y = parseFloat(cells[2].innerText.trim());
        const r = parseFloat(cells[3].innerText.trim()).toFixed(1);

        console.log(`Extracted from table: x=${x}, y=${y}, r=${r}, hit=${hit}`);

        if (!pointsByR[r]) {
            pointsByR[r] = [];
        }

        // Добавляем точку
        pointsByR[r].push({ x, y, hit });
    }

    console.log("Final pointsByR after table processing:", pointsByR);

    const selectedR = parseFloat(document.getElementById("form:r").value).toFixed(1);
    if (selectedR) {
        console.log("Rendering points for r =", selectedR);
        renderPoints(selectedR);
    }
});

function renderPoints(rValue) {
    clearPoints();
    console.log(`Rendering points for R=${rValue}. Current pointsByR:`, pointsByR);

    const points = pointsByR[rValue] || [];
    if (points.length === 0) {
        console.warn(`No points found for R=${rValue}.`);
    } else {
        console.log(`Points found for R=${rValue}:`, points);
    }
    points.forEach(point => {
        console.log(`Rendering point: x=${point.x}, y=${point.y}, hit=${point.hit}`);
        drawPoint(point.x, point.y, rValue, point.hit);
    });
}

function fastrenderPoints(rValue) {
    clearPoints();
    console.log(`Rendering points for R=${rValue}. Current pointsByR:`, pointsByR);

    const points = pointsByR[rValue] || [];
    if (points.length === 0) {
        console.warn(`No points found for R=${rValue}.`);
    } else {
        console.log(`Points found for R=${rValue}:`, points);
    }
    points.forEach(point => {
        console.log(`Rendering point: x=${point.x}, y=${point.y}, hit=${point.hit}`);
        drawPoint(point.x, point.y, rValue, point.hit);
    });

}
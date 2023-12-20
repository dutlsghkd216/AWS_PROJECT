function openPop(row) {
        // Get the data from the clicked row
        var isbn = row.cells[0].textContent;
        var title = row.cells[1].textContent;
        var author = row.cells[2].textContent;
        var publisher = row.cells[3].textContent;

        // Set the values in the popup form
        document.getElementById('isbn').value = isbn;
        document.getElementById('title').value = title;
        document.getElementById('author').value = author;
        document.getElementById('publisher').value = publisher;

        // 현재 날짜 값을 설정하는 코드
        var currentDate = getCurrentDate();
        document.getElementById('signup_year').value = currentDate.year;
        document.getElementById('signup_month').value = currentDate.month;
        document.getElementById('signup_day').value = currentDate.day;

        function getCurrentDate() {
            var today = new Date();
            var year = today.getFullYear();
            var month = ("0" + (today.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더하고 2자리로 포맷
            var day = ("0" + today.getDate()).slice(-2); // 날짜를 2자리로 포맷
            return { year: year, month: month, day: day };
        }

        // Show the popup
        document.getElementById('popup_layer').style.display = 'block';
    }

    // Attach click event to each "예약" button
    var reserveButtons = document.querySelectorAll('.reserve_btn button');
    reserveButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var row = this.closest('tr');
            openPop(row);
        });
    });

    function closePop() {
    document.getElementById("popup_layer").style.display = "none";
}

<!--    예약하기 코드-->
    function collectDataAndSubmit() {
        console.log("Collecting data...");

        // 팝업 창의 데이터 수집
        var isbn = document.getElementById("isbn").value;
        var year = document.getElementById("signup_year").value;
        var month = document.getElementById("signup_month").value;
        var day = document.getElementById("signup_day").value;
        var location = document.querySelector(".bi_select").value;

        // 날짜를 "xxxx-xx-xx" 형식으로 변환
        var formattedDate = year + '-' + (month.length === 1 ? '0' + month : month) + '-' + (day.length === 1 ? '0' + day : day);

        // ReserveDTO에 데이터 담기
        var reserveData = {
            isbn: isbn,
            reserveDate: formattedDate,  // 수정된 부분
            location: location
        };

        // ReserveDTO를 JSON 문자열로 변환
        var reserveJson = JSON.stringify(reserveData);

        // 폼에 JSON 데이터를 추가하여 전송
        var form = document.getElementById("reserveForm");
        var reserveInput1 = document.createElement("input");
        reserveInput1.setAttribute("type", "hidden");
        reserveInput1.setAttribute("name", "isbn");
        reserveInput1.setAttribute("value", isbn);
        form.appendChild(reserveInput1);

        var reserveInput2 = document.createElement("input");
        reserveInput2.setAttribute("type", "hidden");
        reserveInput2.setAttribute("name", "reserveDate");
        reserveInput2.setAttribute("value", formattedDate);
        form.appendChild(reserveInput2);

        var reserveInput3 = document.createElement("input");
        reserveInput3.setAttribute("type", "hidden");
        reserveInput3.setAttribute("name", "location");
        reserveInput3.setAttribute("value", location);
        form.appendChild(reserveInput3);

        // 폼 제출
        form.submit();
        console.log("Submitting form...");
    }
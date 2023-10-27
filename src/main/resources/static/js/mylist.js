$(document).ready(function() {
  $.ajax({
    type: "GET",
    url: "/mypage/mylendlist", // 실제 엔드포인트로 수정
    dataType: "json",
    success: function(response) {
        var tableBody = $("#lendtableBody");
        tableBody.empty(); // 테이블 내용 비우기
        for (var i = 0; i < response.length; i++) {
            var lendData = response[i];

            var row = $("<tr>");
            row.append("<td><input type='checkbox'></td>");
            row.append("<td>" + lendData.isbn + "</td>");
            row.append("<td class='tdt'>" + lendData.bookTitle + "</td>");
            row.append("<td>" + lendData.author + "</td>");
            row.append("<td>" + lendData.publisher + "</td>");
            row.append("<td>" + lendData.pubYear + "</td>");
            row.append("<td>" + lendData.dueDate + "</td>");

            tableBody.append(row);
        }
    },
    error: function(xhr, status, error) {
    }
});
  });
  $(document).ready(function() {
  $.ajax({
    type: "GET",
    url: "/mypage/mywishlist", // 실제 엔드포인트로 수정
    dataType: "json",
    success: function(response) {
        var tableBody = $("#wishtableBody");
        tableBody.empty(); // 테이블 내용 비우기
        for (var i = 0; i < response.length; i++) {
            var wishData = response[i];

            var row = $("<tr>");
            row.append("<td><input type='checkbox'></td>");
            row.append("<td>" + wishData.wishIsbn + "</td>");
            row.append("<td class='tdt'>" + wishData.wishTitle + "</td>");
            row.append("<td>" + wishData.wishAuthor + "</td>");
            row.append("<td>" + wishData.wishPublisher + "</td>");
            row.append("<td>" + wishData.wishYear + "</td>");

            tableBody.append(row);
        }
    },
    error: function(xhr, status, error) {
    }
});
  });
$(document).ready(function() {
  $("#extend-button").on("click", function(e) {
    e.preventDefault(); // 기본 동작 방지 (페이지 이동 방지)
    // 선택된 항목의 ISBN 가져오기
    var selectedIsbn = $("input[type='checkbox']:checked").closest("tr").find("td:nth-child(2)").text(); // ISBN 열을 찾아 ISBN 추출
    if (selectedIsbn) {
      // 선택된 ISBN을 서버로 보내기
      $.ajax({
        type: "POST", // 또는 원하는 HTTP 메서드
        url: "/mypage/lendextend", // 연장을 처리하는 서버 엔드포인트
        data: { isbn: selectedIsbn },
        success: function(response) {
          // 연장에 대한 응답을 처리
          alert("선택한 항목의 기간이 연장되었습니다.");
        },
        error: function(xhr, status, error) {
          // 오류 처리
          console.log(selectedIsbn);
          alert("기간 연장에 실패했습니다.");
        }
      });
    } else {
      alert("항목을 선택하세요.");
    }
  });
});
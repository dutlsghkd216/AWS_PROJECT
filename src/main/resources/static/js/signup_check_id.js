//아이디 사용 가능/중복 메세지 입력

// 가정: 이미 존재하는 아이디 목록
//const existingUsernames = ["user1", "user2", "user3"];

// 아이디 중복 검사 함수
function isUsernameAvailable(username) {
  return !existingUsernames.includes(username);
}

// 아이디 입력 필드와 메시지를 선택합니다.
const usernameInput = document.querySelector(".signup_id");
const availabilityMessage = document.querySelector("#availabilityMessage");

$(document).ready(function(){
        // 아이디 중복 검사
        $("input[name='userId']").on('input', function() {
            var userId = $(this).val();

            // 유효성 검사: 아이디가 비어있는지 확인
            if (userId == '') {
                $("#userIdError").text("아이디를 입력하세요.");
                return;
            }

            // 중복 검사: 아이디가 이미 사용중인지 확인
            $.ajax({
                url: '/idCheck',
                type: 'POST',
                data: JSON.stringify({ userId: userId }),
                contentType: "application/json",
                success: function(data) {
                    if (data.status === 'fail') {
                        $("#userIdError").text(data.message).css("color","red");
                    } else {
                        $("#userIdError").text(data.message).css("color", "blue");
                    }
                },
                error: function(error) {
                    console.error(error);
                }
            });
        });
   });
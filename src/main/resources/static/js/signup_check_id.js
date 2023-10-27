//아이디 사용 가능/중복 메세지 입력

// 가정: 이미 존재하는 아이디 목록
const existingUsernames = ["user1", "user2", "user3"];

// 아이디 중복 검사 함수
function isUsernameAvailable(username) {
  return !existingUsernames.includes(username);
}

// 아이디 입력 필드와 메시지를 선택합니다.
const usernameInput = document.querySelector(".signup_id");
const availabilityMessage = document.querySelector("#availabilityMessage");

// 아이디 입력 필드에서 입력이 발생할 때 아이디 중복 검사를 수행하는 이벤트 리스너를 추가합니다.
usernameInput.addEventListener("input", () => {
  const enteredUsername = usernameInput.value;

  if (isUsernameAvailable(enteredUsername)) {
    availabilityMessage.textContent = "아이디 사용 가능";
    availabilityMessage.style.color = "blue"; // 아이디 사용 가능 메시지의 글씨 색상을 파란색으로 설정
  } else {
    availabilityMessage.textContent = "아이디가 이미 사용 중입니다.";
    availabilityMessage.style.color = "red"; // 아이디가 이미 사용 중인 메시지의 글씨 색상을 빨간색으로 설정
    availabilityMessage.classList.add("shake"); // 떨리는 효과를 추가
  }

  // 메시지를 표시하고 떨린 후에 숨김
  availabilityMessage.style.display = "block";
  setTimeout(() => {
    availabilityMessage.classList.remove("shake");
  }, 200); // 0.2초 뒤에 숨김 및 효과 제거
});

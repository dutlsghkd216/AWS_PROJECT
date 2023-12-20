// 비밀번호 제약사항
function strongPassword (str) {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}
// 비밀번호 확인란 중복 메세지 출력

const passwordInput = document.querySelector(".signup_pwd"); // 비밀번호 입력 필드를 선택합니다.
const confirmPasswordInput = document.querySelector(".signup_pwd-confirm"); // 비밀번호 확인 입력 필드를 선택합니다.
const passwordMatchMessage = document.getElementById("passwordMatchMessage"); // 비밀번호 일치 여부 메시지를 선택합니다.

confirmPasswordInput.addEventListener("input", () => {
  const password = passwordInput.value; // 비밀번호 입력 필드의 값 가져오기
  const confirmPassword = confirmPasswordInput.value; // 비밀번호 확인 입력 필드의 값 가져오기

  if (password === confirmPassword) {
    passwordMatchMessage.textContent = "비밀번호가 일치합니다"; // 비밀번호 일치 메시지 설정
    passwordMatchMessage.style.color = "blue"; // 메시지 텍스트 색상을 파란색으로 설정
    passwordMatchMessage.classList.remove("shake"); // 흔들림 효과 제거
  } else {
    passwordMatchMessage.textContent = "비밀번호가 일치하지 않습니다"; // 비밀번호 불일치 메시지 설정
    passwordM = "red"; // 메시지 텍스트 색상을 빨간색으로 설정
    passwordMatchatchMessage.style.colorMessage.classList.add("shake"); // 흔들림 효과 추가
  }
});

confirmPasswordInput.addEventListener("input", () => {
  const password = passwordInput.value; // 비밀번호 입력 필드의 값 가져오기

  if(strongPassword(password)) {
    passwordMatchMessage2.textContent = "사용할수 있는 비밀번호 입니다."; // 비밀번호 일치 메시지 설정
    passwordMatchMessage2.style.color = "blue"; // 메시지 텍스트 색상을 파란색으로 설정
    passwordMatchMessage2.classList.remove("shake"); // 흔들림 효과 제거
  } else {
    passwordMatchMessage2.textContent = "8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용하세요"; // 비밀번호 불일치 메시지 설정
    passwordMatchMessage2.style.color = "red"; // 메시지 텍스트 색상을 빨간색으로 설정
    passwordMatchMessage2.classList.add("shake"); // 흔들림 효과 추가
  }
});



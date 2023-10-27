// 비밀번호 입력시 표시/숨김

// 비밀번호 입력 필드와 토글 아이콘을 선택합니다.
const passwordFields = document.querySelectorAll(".signup_pwd");
const confirmFields = document.querySelectorAll(".signup_pwd-confirm");
const toggleIcons = document.querySelectorAll(".input__icon");

// 모든 토글 아이콘에 클릭 이벤트 리스너를 추가하여 비밀번호를 표시/숨김 토글 기능을 구현합니다.
toggleIcons.forEach((icon, index) => {
  icon.addEventListener("click", (e) => {
    e.preventDefault(); // 기본 이벤트 동작(아이콘 클릭 시 이미지 변경)을 막습니다.

    const isPassword = index === 0; // 첫 번째 아이콘이 비밀번호 표시/숨김을 제어합니다.

    const inputField = isPassword ? passwordFields[0] : confirmFields[0];
    const toggleIcon = isPassword ? toggleIcons[0] : toggleIcons[1];

    // 아이콘의 src 속성을 변경하여 표시/숨김 아이콘으로 전환합니다.
    toggleIcon.setAttribute(
      "src",
      inputField.getAttribute("type") === "password"
        ? "eye-off.svg" // 비밀번호 표시 아이콘
        : "eye.svg" // 비밀번호 숨김 아이콘
    );

    // 입력 필드의 type 속성을 변경하여 비밀번호를 표시/숨김 토글합니다.
    inputField.setAttribute(
      "type",
      inputField.getAttribute("type") === "password"
        ? "text" // 텍스트 표시
        : "password" // 비밀번호 숨김
    );
  });
});

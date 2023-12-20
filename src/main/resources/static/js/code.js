document.addEventListener('DOMContentLoaded', function () {
  // 인증번호 전송 버튼과 이메일 입력 필드, 메시지 컨테이너, .modify5, .modify6, .code 선택
  const sendCodeButton = document.querySelector('.code');
  const emailInput = document.getElementById('signup_email');
  const messageContainer = document.querySelector('.signup_info_form2');
  const modify5Container = document.querySelector('.modify5');

  // 인증번호 전송 버튼에 대한 이벤트 리스너 추가
  sendCodeButton.addEventListener('click', function () {
    // 이메일 주소 가져오기
    const email = emailInput.value.trim();

    // 이메일이 빈칸이면 포커스를 이메일 입력란으로 이동하고 함수 종료
    if (email === '') {
      emailInput.focus();
      return;
    }

    // 기존 메시지가 없는 경우에만 메시지를 표시
    if (!messageContainer.querySelector('.message')) {
      // 메시지를 표시
      const messageElement = document.createElement('div');
      messageElement.innerText = '입력한 이메일로 인증번호가 발송됩니다. 인증번호를 확인해주세요.';
      messageElement.style.color = 'gray';
      messageElement.classList.add('message');

      // .signup_info_form2 아래에 메시지 추가
      messageContainer.appendChild(messageElement);

      // .modify5 태그 표시
      modify5Container.style.display = 'block';

      // .modify5 input 태그 표시
      const inputElement = document.createElement('input');
      inputElement.type = 'text';
      inputElement.placeholder = '인증번호를 입력하세요';
      inputElement.classList.add('modify6');

      // "인증번호 확인" 버튼 표시
      const verifyCodeButton = document.createElement('button');
      verifyCodeButton.innerText = '인증번호 확인';
      verifyCodeButton.classList.add('code');

    }
  });
});
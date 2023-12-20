//    이메일 인증
    document.getElementById('emailVerification').addEventListener('click', function() {
           var emailPrefix = document.getElementById('emailPrefix').value;
           var emailDns = document.getElementById('emailDns').value;
           var userEmail = emailPrefix + '@' + emailDns;

           console.log(userEmail);

           if (userEmail.trim() !== '') {
               $.ajax({
                   url: '/security/mailconfirm',
                   method: 'POST',
                   contentType: 'application/json',
                   dataType: 'text', // 추가
                   data: JSON.stringify({ email: userEmail }),
                   success: function(data) {
//                       console.log('서버 응답 데이터:', data);
                       alert("이메일이 발송되었습니다. 확인후 인증번호를 입력해주세요");
                       document.getElementById('modify5').style.display = 'block';
                       console.log('인증 코드: ', data);
                   },
                   error: function(error) {
                       console.error('Error:', error);
                   }
               });
           } else {
               alert('이메일을 입력하세요.');
           }
       });


       /* 회원가입 이메일 인증확인 */
       $('#submitVerification').click(function() {
           var inputCode = $('#verificationInput').val();

           $.ajax({
               url: '/security/checkCode',
               type: 'POST',
               data: JSON.stringify({ code: inputCode }),
               contentType: "application/json", // 'Content-Type' 헤더를 'application/json'으로 설정
               success: function(result) {
                   if (result) {
//                       alert('인증 성공');
                       $("#userEmailError2").text("인증되었습니다.");
                   } else {
//                       alert('인증 실패');
                       $("#userEmailError2").text("인증에 실패하셨습니다. 다시해주세요.");
                   }
               }
           });
       });

    // 이메일 도메인 선택 엘리먼트와 이메일 입력란 가져오기
    var emailDomainSelect = document.getElementById('email_domain');
    var emailInputPrefix = document.getElementById('emailDns');

    // 도메인 선택 시 이메일 입력란 업데이트
    emailDomainSelect.addEventListener('change', function() {
      var selectedDomain = emailDomainSelect.value;

      if (selectedDomain === '직접입력') {
        emailInputPrefix.value = '';
      } else {
        emailInputPrefix.value = selectedDomain;
      }
    });


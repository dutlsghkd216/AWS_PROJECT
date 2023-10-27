    // 이메일 도메인 선택 엘리먼트와 이메일 입력란 가져오기
    var emailDomainSelect = document.getElementById('email_domain');
    var emailInputPrefix = document.getElementById('signup_email_prefix');
  
    // 도메인 선택 시 이메일 입력란 업데이트
    emailDomainSelect.addEventListener('change', function() {
      var selectedDomain = emailDomainSelect.value;
      
      if (selectedDomain === '직접입력') {
        emailInputPrefix.value = '';
      } else {
        emailInputPrefix.value = selectedDomain;
      }
    });
function deleteValue(){
  // 체크박스에서 선택한 isbn가져오기
  var selectedIsbn = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(function(checkbox) {
    return checkbox.value;
  });
  // 선택한 책 없는 경우 함수 종료
  if (selectedIsbn.length === 0){
    alert('삭제할 책을 선택하세요.');
    return;
    }

   // 각 책에 대해 delete요청 보내기
   selectedIsbn.forEach(function(isbn) {
    fetch('/books/remove/' + isbn, {
      method: 'DELETE',
   })
    .then(function(response) {
      if(!response.ok){
        throw new Error('Error deleting book');
      }
      return response.text();
      })
      .then(function(message) {
        alert(message);
        location.reload();  // 페이지를 새로고침
      })
      .catch(function(error){
        alert(error);
        });
    });
  }

  // 체크박스 한건씩만
  // 체크박스들을 가져옵니다.
var checkboxes = document.querySelectorAll('input[type="checkbox"]');

// 각 체크박스에 대해 이벤트 리스너를 설정합니다.
checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', function(event) {
        // 선택된 체크박스 외의 모든 체크박스를 해제합니다.
        checkboxes.forEach(function(otherCheckbox) {
            if (otherCheckbox !== checkbox) {
                otherCheckbox.checked = false;
            }
        });
    });
});

// 출생년도 옵션 생성
var yearSelect = document.getElementById('signup_year');
var currentYear = new Date().getFullYear();
for (var year = currentYear; year >= 1900; year--) {
  var option = document.createElement('option');
  option.value = year;
  option.textContent = year;
  yearSelect.appendChild(option);
}

// 월 옵션 생성
var monthSelect = document.getElementById('signup_month');
for (var month = 1; month <= 12; month++) {
  var option = document.createElement('option');
  option.value = month;
  option.textContent = month;
  monthSelect.appendChild(option);
}

// 일 옵션 생성
var daySelect = document.getElementById('signup_day');

// 월이나 년도 변경 시 일 옵션 업데이트
yearSelect.addEventListener('change', updateDays);
monthSelect.addEventListener('change', updateDays);

// 초기에 일 옵션 업데이트
updateDays();

function updateDays() {
  var selectedYear = yearSelect.value;
  var selectedMonth = monthSelect.value;
  var daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();

  // 일 옵션 업데이트
  daySelect.innerHTML = '';
  for (var day = 1; day <= daysInMonth; day++) {
    var option = document.createElement('option');
    option.value = day;
    option.textContent = day;
    daySelect.appendChild(option);
  }
}
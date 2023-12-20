function openPop(btn){
    var row = btn.parentNode.parentNode;
    // 클릭시 데이터 받아오기
    var userId = row.cells[1].textContent;
    var userNameField = row.cells[3].textContent;
    var userEmailField = row.cells[2].textContent;
    var userBirthField = row.cells[4].textContent;
    var userRegisterDateField = row.cells[5].textContent;
    var userRoleField = row.cells[6].textContent;

    // 팝업창에 값 넣기
    document.getElementById('userId').value = userId;
    document.getElementById('userNameField').value = userNameField;
    document.getElementById('userEmailField').value = userEmailField;
    document.getElementById('userBirthField').value = userBirthField;
    document.getElementById('userRegisterDateField').value = userRegisterDateField;
    document.getElementById('userRoleField').value = userRoleField; // 회원유형 설정

    // Show the popup
    document.getElementById('popup_layer').style.display = 'block';
}
    function closePop() {
    document.getElementById("popup_layer").style.display = "none";
    }

// 회원 수정
function saveUserData() {
    // Get values from the form
    var userId = document.getElementById("userId").value;
    var userEmail = document.getElementById("userEmailField").value;
    var userRole = document.getElementById("userRoleField").value;

    // Create a JavaScript object with the data
    var userData = {
        userId: userId,
        userEmail: userEmail,
        userRole: userRole,
    };

    // Make an AJAX request to the server
    $.ajax({
        type: "POST",
        url: "/admin/saveUserData",  // Update with the correct endpoint
        data: JSON.stringify(userData),
        contentType: "application/json",
        success: function (response) {
            console.log("Server response: ", response);
            console.log("User data saved successfully");
            // Update the page with the new data
//            console.log("Updating userEmailField");
//            document.getElementById("userEmailField").value = response.userEmail;
//            console.log("Updating userRoleField");
//            document.getElementById("userRoleField").value = response.userRole;
            closePop();
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
//            console.error("Error saving user data", error);
            console.error("AJAX error: ", textStatus, ", ", errorThrown);
            // Handle the error as needed
//            closePop();
        }
    });
}

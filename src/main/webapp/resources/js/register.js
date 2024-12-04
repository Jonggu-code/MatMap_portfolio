 // 프로필
 function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('profileImage');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}

 function validateAndSubmit() {
     // 입력 필드 값 가져오기
     const userId = document.getElementById("user_id").value.trim();
     const password = document.getElementById("password").value.trim();
     const email = document.getElementById("email").value.trim();
     const phoneNumber = document.getElementById("phone_number").value.trim();
     const gender = document.querySelector('input[name="gender"]:checked'); // 선택된 성별

     // 에러 메시지를 표시할 영역 초기화
     const msgDiv = document.getElementById("msg");
     msgDiv.textContent = ""; // 기존 메시지 제거
     msgDiv.style.color = "red";

     // 유효성 검사
     if (!userId || /[^a-zA-Z0-9]/.test(userId)) {
         msgDiv.textContent = "아이디는 필수 입력 항목이며, 특수문자를 포함할 수 없습니다.";
         return false; // 폼 제출 중단
     }

     if (!password) {
         msgDiv.textContent = "비밀번호는 필수 입력 항목입니다.";
         return false;
     }

     if (!email || !email.includes("@")) {
         msgDiv.textContent = "유효하지 않은 이메일 형식입니다.";
         return false;
     }

     if (!phoneNumber || !/^\d{3}-\d{4}-\d{4}$/.test(phoneNumber)) {
         msgDiv.textContent = "전화번호는 000-0000-0000 형식이어야 합니다.";
         return false;
     }

     if (!gender || (gender.value !== "M" && gender.value !== "F")) {
         msgDiv.textContent = "성별은 '남' 또는 '여' 중 하나를 선택해야 합니다.";
         return false;
     }

     // 모든 유효성 검사를 통과하면 폼을 제출
     return true;
 }

// //마이페이지
// document.getElementById('signup_form').addEventListener('submit', function(event) {
//     event.preventDefault();  // 기본 제출 방지
//
//     // 사용자 이름 가져오기
//     const name = document.getElementById('name').value;
//
//     // 로컬 스토리지에 이름 저장
//     localStorage.setItem('username', name);
//
//     // 회원가입 완료 후 마이페이지로 이동
//     window.location.href = 'mypage.html';  // 마이페이지로 리다이렉션
// });
 // 프로필
 function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('profileImage');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}
//마이페이지 
document.getElementById('signup_form').addEventListener('submit', function(event) {
    event.preventDefault();  // 기본 제출 방지

    // 사용자 이름 가져오기
    const name = document.getElementById('name').value;

    // 로컬 스토리지에 이름 저장
    localStorage.setItem('username', name);

    // 회원가입 완료 후 마이페이지로 이동
    window.location.href = 'mypage.html';  // 마이페이지로 리다이렉션
});
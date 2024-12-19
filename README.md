<p align="center">
  <img src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/logo_500x500.png" alt="MatMap_logo" />
</p>

## 🖥️ 프로젝트 소개
사람들이 맛집을 검색함에 있어 가장 원하는 정보, 정제된 정보를 전달해주는 사이트​

## 🕰️ 개발기간
2024.11.18 ~ 2024.12.06

## 🧑‍🤝‍🧑  프로젝트 인원
- 팀장 : 박종찬 - 메인 페이지, 상세 페이지, 랭킹 페이지, 찜한 맛집 페이지, 내가 작성한 리뷰 페이지 구성
- 팀원 : 심재용 - 로그인 페이지, 회원가입 페이지, 게시판 페이지 구성 및 PPT 제작
- 팀원 : 곽채연 - 회원가입, 로그인, 마이페이지, 내가 작성한 리뷰 내가 찜한 식당 관련 API 구현 및 html -> JSP변환
- 팀원 : 강주헌 - 상세 페이지, 리뷰 관련 API 구현 및 html -> JSP변환
- 팀원 : 임호준 - ERD 설계, 이미지 저장 및 수정, 랭킹 조회, 메인페이지 태그별 조회 및 식당 상세 조회 API 구현. 프로젝트 버전 관리, html -> JSP변환 및 AJAX 조회 구현

## ⚙️ 개발 환경
- `Java 11`
- `Spring 5.0.7`
-  `MySQL 8.x`
-  `MyBatis 3.5.11`
-  `JSP-api 2.1` , `jstl 1.2`
-  `Junit 4.12`
-  `Javascript` , `JQuery 1.12.0`
-  `lombok 1.18.36`
-  `IntelliJ` , `VSCode`
-  `Notion`

## 📌주요 기능

  <details> 
    <summary>로그인</summary>
    <img align="top" style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/로그인.png" alt="login_page" />
    <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/로그인%20페이지%20(정보없음).png" alt="login_page_not" />
    <ul>
      <li> DB에 저장된 아이디/비밀번호와 비교 후 일치하지 않으면 '일치하는 정보가 없습니다' 메세지 띄움 </li>
      <li> 쿠키 활용해 '아이디 기억하기' 기능 사용 </li>
    </ul>
  </details>
  
***

  <details> 
    <summary>회원가입</summary>
    <img src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/회원가입.png" alt="register_page" />
    <ul>
      <li> 아이디 중복 체크 및 비밀번호 확인 </li>
      <li> 프로필 이미지 등록가능 </li>
      <li> 아이디, 비밀번호 유효성 체크 기능 </li>
      <li> 자기소개를 제외한 정보에 null이나 undefined 있을경우 경고 메세지 </li>
    </ul>
  </details>

  ***

  <details> 
    <summary>마이페이지</summary>
    <div>
      <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/마이페이지.png" alt="register_page" /> 
      <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/마이페이지(정보없음).png" alt="register_page_empty" />
      <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/마이페이지%20반응형%201.png" alt="register_page_media_1" />
      <img align="top" style="width: 26%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/마이페이지%20반응형%202.png" alt="register_page_media_2" />
    </div>
    <ul>
      <li> 세션 정보 조회후 해당 회원의 마이페이지 조회 </li>
      <li> 회원정보 수정 기능 (프로필 이미지 변경 가능) </li>
      <li> 유저가 찜한 음식점 & 작성한 후기 -> 찜한 시간 & 작성한 시간 순으로 상위 5개 조회 </li>
      <li> 마이페이지 정보 없을시 default 화면 구성 </li>
      <li> 반응형 페이지 구성 </li>
    </ul>
  </details>

  ***

  <details> 
    <summary>메인페이지</summary>
    <div>
      <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/메인페이지.png" alt="index"/>
      <img style="width: 48%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/메인화면(정보없음).png" alt="index_empty"/>
    </div>
    <ul>
      <li> 카카오맵 API 활용 - 마커 생성 / 인포윈도우 커스텀 </li>
      <li> AJAX의 비동기 처리방식으로 음식점 검색 시 음식점 리스트업 기능 구현 </li>
      <li> 검색 기록 없을 시 ‘검색 결과가 없어요’ 창 띄우기 </li>
    </ul>
  </details>

  <details> 
    <summary>메인페이지 - 세부기능</summary>
    <div>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/지역%20검색.PNG" alt="index_func1"/>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/정렬순서.PNG" alt="index_func2"/>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/태그%20검색.PNG" alt="index_func3"/>
      <img align="top" style="width: 24%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/유저메뉴(비로그인).PNG" alt="index_func4"/>
      <img align="top" style="width: 24%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/유저메뉴(로그인).PNG" alt="index_func5"/>
    </div>
    <ul>
      <li> 카카오맵 API 활용 - 마커 생성 / 인포윈도우 커스텀 </li>
      <li> 지도 표시지역 / 맛집 정렬 순서 / 전체 박스 클릭으로 각 검색 목적에 맞게 식당 검색 가능 </li>
      <li> 버튼 클릭하여 현재 보고있는 구역 내의 음식점 재검색 </li>
      <li> 게스트 / 유저 간 게스트박스 메뉴 상이 </li>
    </ul>
  </details>

  ***
  
  <details> 
    <summary>음식점 디테일 페이지</summary>
    <div>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/음식점%20세부페이지.jpg" alt="detail"/>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/음식점_세부페이지_반응형_1%20.jpg" alt="detail_media_1"/>
      <img align="top" style="width: 33%;" src="https://github.com/Jonggu-code/MatMap_portfolio/blob/main/img/음식점_세부페이지_반응형_2.jpg" alt="index_media_2"/>
    </div>
    <ul>
      <li> 카카오맵 API 활용 - 마커 생성 / 인포윈도우 커스텀 </li>
      <li> 지도 표시지역 / 맛집 정렬 순서 / 전체 박스 클릭으로 각 검색 목적에 맞게 식당 검색 가능 </li>
      <li> 버튼 클릭하여 현재 보고있는 구역 내의 음식점 재검색 </li>
      <li> 게스트 / 유저 간 게스트박스 메뉴 상이 </li>
    </ul>
  </details>
  



# 랭킹페이지
# 리뷰작성
# 내가 찜한 식당 페이지
# 내가 리뷰 작성한 식당 페이지

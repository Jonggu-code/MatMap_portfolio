var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(37.500227, 127.037003),
    level: 4
};

var map = new kakao.maps.Map(container, options);

kakao.maps.event.addListener(map, 'click', function(mouseEvent){
    var latlng = mouseEvent.latLng;

    var message = `클릭한 위치의 위도는 ${latlng.getLat()} 이고 경도는 ${latlng.getLng()} 입니다.`

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;
});

// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'zoom_changed', function() {        
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    var message = '현재 지도 레벨은 ' + level + ' 입니다';
    var resultDiv = document.getElementById('result');  
    resultDiv.innerHTML = message;
});
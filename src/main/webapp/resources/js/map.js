var mapContainer = document.getElementById('map');
var mapOptions = {
    center: new kakao.maps.LatLng(37.500227, 127.037003),
    level: 4
};

var map = new kakao.maps.Map(mapContainer, mapOptions);

var points = [
    new kakao.maps.LatLng(37.509548471097105, 128.0159250467026),
    new kakao.maps.LatLng(37.483543665468815, 127.02584661602873),
    new kakao.maps.LatLng(37.49464806546498, 127.06312211842112),
    new kakao.maps.LatLng(37.51887323827288, 127.05029230479465)
]
var path = [
    new kakao.maps.LatLng(44.450417966392685, 115.02830047854872),
    new kakao.maps.LatLng(44.28630877074187, 139.7926056198738),
    new kakao.maps.LatLng(31.15587112811611 , 137.8097657229059),
    new kakao.maps.LatLng(31.13113650304299 , 117.00978991779066)
];

var hole = [
    new kakao.maps.LatLng(37.50956646665349, 127.0161060170077),
    new kakao.maps.LatLng(37.48345352987959, 127.02600487552272),
    new kakao.maps.LatLng(37.48588101573885, 127.0437461378583),
    new kakao.maps.LatLng(37.486186878467315, 127.04502399350439),
    new kakao.maps.LatLng(37.48651096333826, 127.0457478313627),
    new kakao.maps.LatLng(37.49457592447882, 127.06323513913003),
    new kakao.maps.LatLng(37.51885524228435 , 127.05023573368683),
    new kakao.maps.LatLng(37.51721889910142, 127.041241947207),
];

var polygon = new kakao.maps.Polygon({
    map: map,
    path: [path, hole],
    strokeWeight: 2,
    strokeColor: '#ff9625',
    strokeOpacity: 0.8,
    fillColor: '#5d350a',
    fillOpacity: 0.7 
})

kakao.maps.event.addListener(map, 'click', function(mouseEvent){
    var latlng = mouseEvent.latLng;

    var message = `클릭한 위치의 위도는 ${latlng.getLat()} 이고 경도는 ${latlng.getLng()} 입니다.`

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;
});

var bounds = new kakao.maps.LatLngBounds();

for (i=0; i < points.length; i++){
    bounds.extend(points[i]);
}

function setBounds() {
    map.setBounds(bounds);
}
// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다

let isHandler = false;
kakao.maps.event.addListener(map, 'zoom_changed', function(event) {       
    var level = map.getLevel();

    var message = '현재 지도 레벨은 ' + level + ' 입니다';

    var resultDiv = document.getElementById('result');  
    resultDiv.innerHTML = message;
});


function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

//이거 그 메인화면 우측상단에있는 현재보는위치기준으로 식당조회버튼임
const btn = document.getElementsByClassName('focus_map')[0]
function clear_marker(){
    for(let i=0; i < tmp_position.length; i++){
        // marker.setMap(null)
        // console.log(marker.positions, marker.title)
        marker_list[i].setMap(null);
    }
}

var tmp_position = [];
function xy_location(data){
    for(let i = 0; i<data.length; i++){
        tmp_position[i] = ({
            image : data[i].image_url,
            name : data[i].name,
            score : data[i].total_score_count,
            review : data[i].total_review_count,
            address : data[i].address,
            business_hour : data[i].business_hours_dto,
            business_now : data[i].today_business_state,
            latlng : new kakao.maps.LatLng(data[i].loc_x, data[i].loc_y)
        })
    }
}
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
var marker_list = [];
function create_marker(){
    for(let i = 0; i < tmp_position.length; i++){
        var imageSize = new kakao.maps.Size(24, 35);
        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var marker = new kakao.maps.Marker({
            map:map,
            position: tmp_position[i].latlng,
            title : tmp_position[i].name,
            image: markerImage,
        });
        marker_list[i] = marker;

        var info_window = new  kakao.maps.InfoWindow({
            content : tmp_position[i].name,
            image : tmp_position[i].image_url,
        })
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, info_window));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(info_window));
    }
}

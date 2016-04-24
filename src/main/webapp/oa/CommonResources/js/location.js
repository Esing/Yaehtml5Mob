
function getLocation(){
   var options={
       enableHighAccuracy:true,
       maximumAge:1000
   }
   if(navigator.geolocation){
       //浏览器支持geolocation
	   navigator.geolocation.getCurrentPosition(onSuccess,onError,options);
   }else{
       //浏览器不支持geolocation
	   mui.alert('您的浏览器不支持地理位置定位');
   }
}
	
//成功时
function onSuccess(position){
       //返回用户位置
    //经度
    var longitude =position.coords.longitude;
    //纬度
    var latitude = position.coords.latitude;

	var longitudeAndlatitude =[longitude,latitude];
	return longitudeAndlatitude;
}
 
//失败时
function onError(error){
   switch(error.code){
   case 1:
	   mui.alert("位置服务被拒绝");
       break;
   case 2:
	   mui.alert("暂时获取不到位置信息");
	   break;
   case 3:
	   mui.alert("获取信息超时");
	   break;
   case 4:
	   mui.alert("未知错误");
       break;
   }

}

function fD(a, b, c) {
	for (; a > c; )
		a -= c - b;
	for (; a < b; )
		a += c - b;
	return a;
};
function jD(a, b, c) {
	b != null && (a = Math.max(a, b));
	c != null && (a = Math.min(a, c));
	return a;
};
function yk(a) {
	return Math.PI * a / 180
};
function Ce(a, b, c, d) {
	var dO = 6370996.81;
	return dO * Math.acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
};
/**
 * 计算坐标点距离
 * eg：alert(getDistance({lng : 106.486654, lat: 29.490295},{lng : 106.581515,lat :29.615467}));
 * @param a
 * @param b
 * @returns {Number}
 */
function getDistance(a, b) {
	if (!a || !b)
		return 0;
	a.lng = fD(a.lng, -180, 180);
	a.lat = jD(a.lat, -74, 74);
	b.lng = fD(b.lng, -180, 180);
	b.lat = jD(b.lat, -74, 74);
	return Ce(yk(a.lng), yk(b.lng), yk(a.lat), yk(b.lat));
};

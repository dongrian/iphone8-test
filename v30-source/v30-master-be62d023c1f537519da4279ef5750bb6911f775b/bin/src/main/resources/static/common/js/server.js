var server = {};
var serverUrl = ""
	
/**
 * OTP 생성
 * 
 * type: POST
 */
server.otp = function(ctn, callback) {
	$.ajax({
		url : serverUrl + "/api/otp?ctn=" + ctn,
		type: "POST",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * OTP 인증 
 * 
 * type: GET
 */
server.certificate = function(ctn, certNumber, callback) {
	$.ajax({
		url : serverUrl + "/api/otp/certificate?ctn=" + ctn + "&certNumber=" + certNumber,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 예약가입 등록
 * 
 * type: POST
 * dataType: josn
 */
server.order = function(order, callback) {
	var data = JSON.stringify(order);
	$.ajax({
		url : serverUrl + "/api/order",
		type: "POST",
		contentType: "application/json",
		data: data,
		dataType: "json",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 예약가입 취소
 * 
 * type: POST
 * dataType: josn
 */
server.cancelOrder = function(cancel, callback) {
	var data = JSON.stringify(cancel);
	$.ajax({
		url : serverUrl + "/api/order/cancel",
		type: "POST",
		contentType: "application/json",
		data: data,
		dataType: "json",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 방문매장 등록
 * 
 * type: POST
 */
server.updateVisitStore = function(reqSeqNo, storeCd, callback) {
	$.ajax({
		url : serverUrl + "/api/order/store?reqSeqNo=" + reqSeqNo + "&storeCd=" + storeCd,
		type: "POST",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 광역시/도 조회 
 * 
 * type: GET
 */
server.address1 = function(callback) {
	$.ajax({
		url : serverUrl + "/api/address1",
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 구/시/군 조회 
 * 
 * type: GET
 */
server.address2 = function(address1, callback) {
	$.ajax({
		url : serverUrl + "/api/address2?address1=" + address1,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 방문매장 조회 
 * 
 * type: GET
 */
server.stores = function(address1, address2, callback) {
	$.ajax({
		url : serverUrl + "/api/stores?address1=" + address1 + "&address2=" + address2,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 매장명 검색
 * 
 * type: GET
 */
server.searchStores = function(query, callback) {
	$.ajax({
		url : serverUrl + "/api/stores/search?query=" + query,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};
/**
 * 광역시/도 조회 
 * 
 * type: GET
 */
server.exAddress1 = function(callback) {
	$.ajax({
		url : serverUrl + "/api/exstore/address1",
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 구/시/군 조회 
 * 
 * type: GET
 */
server.exAddress2 = function(address1, callback) {
	$.ajax({
		url : serverUrl + "/api/exstore/address2?address1=" + address1,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 방문매장 조회 
 * 
 * type: GET
 */
server.exStores = function(address1, address2, callback) {
	$.ajax({
		url : serverUrl + "/api/exstores?address1=" + address1 + "&address2=" + address2,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};

/**
 * 매장명 검색
 * 
 * type: GET
 */
server.searchExStores = function(query, callback) {
	$.ajax({
		url : serverUrl + "/api/exstores/search?query=" + query,
		type: "GET",
		success: function(data) {
			if ($.isFunction(callback)) {
				callback(data);
			}
		}
	});
};
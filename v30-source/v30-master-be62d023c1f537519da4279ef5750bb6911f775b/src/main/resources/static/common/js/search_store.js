function sendExStoreNameSearch(){
	var query = $.trim($("input[name=storename]").val());
	if (query == "") {
		alert("검색어를 입력해주세요.");
	} else {
		server.searchExStores(encodeURIComponent(query), searchExStoresCallback);
	}
}

function searchExStoresCallback(response) {
    if (response.status === "OK") {
        displayExStores(response.data);
    } else if (response.status === "EMPTY_RESULT") {
    	if ($("#store_list").length > 0) {
    		$("#store_list").empty();
    		$("#store_list").append('<div class="searchDefaultMsg">검색된 매장이 없습니다.</div>');
    	} else {
    		$("#m_store_list").empty();
    		$("#m_store_list").append('<div class="txt_before">검색된 매장이 없습니다.</div>');
    	}
        return;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function displayExStores(stores) {
	if ($("#store_list").length > 0) {
		$("#store_list").empty();
		for (var i = 0; i < stores.length; i++) {
			var store = stores[i];
			var html = '<div class="storeListBlock">';
			html += '<div class="listElement storeField">' + store.dlrNm + '</div>';
			html += '<div class="listElement addField">' + store.fullAddress + '</div>';
//			html += '<div class="listElement phoneField">' + store.phoneNumber + '</div>';
			html += '<div class="listElement mapbtn"><a href="javascript:drawMap(\'' + store.fullAddress + '\', \'' + store.dlrNm + '\');"><img src="images/btn_map_view.png" /></a></div>';
			html += '</div>';
			$("#store_list").append(html);
		}
	} else {
		$("#m_store_list").empty();
		$("#m_store_list").append("<ul></ul>");
		for (var i = 0; i < stores.length; i++) {
			var store = stores[i];
			var html = '<li>';
			html += '<label for="address' + i + '">' + store.dlrNm + '<br/>';
			html += store.fullAddress + '</label>';
			html += '<div class="mapbtn"><a href="javascript:drawMap(\'' + store.fullAddress + '\', \'' + store.dlrNm + '\');"><img src="images/btn_map_view.png" /></a></div>';
			html += '</li>';
			$("#m_store_list ul").append(html);
		}
		
	}
}

function sendExLocalNameSearch(){
	var address1 = $.trim($("#store_area_list option:selected").val());
	var address2 = $.trim($("#store_sublist option:selected").val());
	if (address1 == "" || address2 == "") {
		alert("지역을 선택해주세요.");
	} else {
		server.exStores(encodeURIComponent(address1), encodeURIComponent(address2), exStoresCallback);
	}
}

function exStoresCallback(response) {
    if (response.status === "OK") {
        displayExStores(response.data);
    } else if (response.status === "EMPTY_RESULT") {
    	if ($("#store_list").length > 0) {
    		$("#store_list").empty();
    		$("#store_list").append('<div class="searchDefaultMsg">검색된 매장이 없습니다.</div>');
    	} else {
    		$("#m_store_list").empty();
    		$("#m_store_list").append('<div class="txt_before">검색된 매장이 없습니다.</div>');
    	}      
    	return;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function getExAddress1() {
	server.exAddress1(exAddress1Callback);
}

function exAddress1Callback(response) {
	if (response.status === "OK") {
		var $address1 = $("#store_area_list");
		var adds = response.data;
		for (var i = 0; i < adds.length; i++) {
			var html = '<option value="' + adds[i] + '">' + adds[i] + '</option>';
			$address1.append(html);
		}
	}
}

function getExAddress2(address1) {
	server.exAddress2(encodeURIComponent(address1), exAddress2Callback);
}

function exAddress2Callback(response) {
    if (response.status === "OK") {
        var $address2 = $("#store_sublist");
        var adds = response.data;
        $address2.empty();
        $address2.append('<option value="" selected="selected">== 구/군 ==</option>');
    	for (var i = 0; i < adds.length; i++) {
    		var html = '<option value="' + adds[i] + '">' + adds[i] + '</option>';
    		$address2.append(html);
    	}
    }
}


function drawMap(address, dlrNm) {
	
	$("#mapDlrNm").text('');
	$("#mapAddress").text('');
	
	$('#imap').empty();
	
	/*
	var host = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port: '');
	var url = host + '/map?address=' + encodeURIComponent(address) + '&dlrNm=' + encodeURIComponent(dlrNm);
	$("#imap").attr('src', url);
	*/
	
	popHeightControl('popMapView','block','top');
	
	$("#mapDlrNm").text(dlrNm);
	$("#mapAddress").text(address);
	
	setMap(address, dlrNm)
}

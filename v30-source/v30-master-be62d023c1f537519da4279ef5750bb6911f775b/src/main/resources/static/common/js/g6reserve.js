var sliderID = 0;
var colorID = -1;
var searchTypeID = 0;
var modTypeID = -1;
var phoneSlider;
var auth = false;
var intervals = [];

var isColorChker = true;

function sendVReventPage(){
	window.open('http://event1.upluslte.co.kr/v30_vr/?src=image&kw=00032C&utm_source=v30%20home%20store&utm_medium=reserve4', '_blank');
}

/******************************** 2017-08-11 ****************************************/
function openStoreMap(mapID){
	$('.mapPopBlock').css('display', 'block');
}
function closeMapPop(){
	$('.mapPopBlock').css('display', 'none');
}

/******************************** 2017-08-11 ****************************************/

function sendMainOutLink(idx){

}
function sendMainOutLinkM(idx){

}

function sendReserveUserInfo() {
	var name = $.trim($("input[name=username]").val());
	var phonNo = $.trim($("input[name=handphone]").val());
	var giftCd = "Gift002";
	var colrCd = "";
	var colrNm = "";
	var modTypCd = "";
	var sizeCd = "";
	var deviceDesc = "";
	
	if (colorID === 0) {
		colrCd = "CO005";
		colrNm = "모로칸 블루";
	} else if (colorID === 1) {
		colrCd = "CO006";
		colrNm = "오로라 블랙";
	} else if (colorID === 2) {
		colrCd = "CO007";
		colrNm = "클라우드 실버";
	} else if (colorID === 3) {
		colrCd = "CO008";
		colrNm = "라벤더";
	}
	
	if (modTypeID === 0) {
		modTypCd = "PH009";
		sizeCd = "SZ006";
		deviceDesc = "V30 (64GB)";
	} else if (modTypeID === 1) {
		modTypCd = "PH0010";
		sizeCd = "SZ007";
		deviceDesc = "V30+ (128GB)";
	}
	var agree1 = $("input:checkbox[id='btnAgree']").is(":checked");
	var agree2 = $("input:checkbox[id='btnAgree2']").is(":checked");
	var regExp = /^[0-9]+$/;
	
	// 입력값 체크
	if (name == ""){
		alert("이름을 입력해주세요.")
	} else if (phonNo == ""){
		alert("휴대폰번호를 입력해주세요.")
	} else if (!regExp.test(phonNo) || phonNo.length < 10){
		alert("잘못된 휴대폰번호입니다. 정확한 번호를 입력하세요.")
	} else if (!agree1){
		alert("개인정보 수집 이용 동의가 필요합니다.");
	} else if (!agree2) {
		alert("개인정보 처리 위탁 동의가 필요합니다.");
	} else if (giftCd == ""){
		alert("사은품을 선택해주세요.")
	} else if (colrCd == ""){
		alert("컬러를 선택해주세요.")
	} else if (modTypCd == ""){
		alert("기기를 선택해주세요.")
	} else {
		var message = deviceDesc+ " / " + colrNm + " 을(를) 선택하셨습니다.\n"
				+ name + "님 / " + phonNo + "로 예약됩니다.\n"
				+ "예약가입 접수를 완료 하시겠습니까?";
		if (confirm(message) == true) {
			var order = {
					name: name,
					phonNo: phonNo,
					colrCd: colrCd,
					giftCd: giftCd,
					modTypCd: modTypCd,
					sizeCd: sizeCd
			};
			
			server.order(order, orderCallback);
		} else {
			return;
		}
	}
}

function orderCallback(response) {

    if (response.status === "OK") {
        location.href = "/reserve3?reqSeqNo=" + response.data;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function sendStoreNameSearch(){
	var query = $.trim($("input[name=storename]").val());
	if (query == "") {
		alert("검색어를 입력해주세요.");
	} else {
		server.searchStores(encodeURIComponent(query), searchStoresCallback);
	}
}

function searchStoresCallback(response) {
    if (response.status === "OK") {
        displayStores(response.data);
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

function displayStores(stores) {
	if ($("#store_list").length > 0) {
		$("#store_list").empty();
		for (var i = 0; i < stores.length; i++) {
			var store = stores[i];
			var html = '<div class="storeListBlock">';
			html += '<div class="listElement storeField">' + store.dlrNm + '</div>';
			html += '<div class="listElement addField">' + store.fullAddress + '</div>';
//			html += '<div class="listElement phoneField">' + store.phoneNumber + '</div>';
			html += '<div class="listElement chooseField"><input type="radio" name="storeCd" data-store-name="' + store.dlrNm + '" value="' + store.orgCd + ',' + store.dlrCd + '"/></div>';
			html += '<div class="listElement mapbtn"><a href="javascript:drawMap(\'' + store.fullAddress + '\', \'' + store.dlrNm + '\');"><img src="images/btn_map_view.png" width="60%"/></a></div>';
			html += '</div>';
			$("#store_list").append(html);
		}
	} else {
		$("#m_store_list").empty();
		$("#m_store_list").append("<ul></ul>");
		for (var i = 0; i < stores.length; i++) {
			var store = stores[i];
			var html = '<li><a href="#">';
			html += '<label for="address' + i + '">' + store.dlrNm + '<br/>';
			html += store.fullAddress + '</label>';
			html += '<span><input type="radio" name="storeCd" data-store-name="' + store.dlrNm + '" id="address' + i + '" class="ipt_rdo" value="' + store.orgCd + ',' + store.dlrCd + '"/></span>';
			html += '</a>';
			html += '<div class="listElement mapbtn"><a href="javascript:drawMap(\'' + store.fullAddress + '\', \'' + store.dlrNm + '\');"><img src="images/btn_map_view.png" width="60%"/></a></div>';
			html += '</li>';
			$("#m_store_list ul").append(html);
		}
		
	}
}

function sendLocalNameSearch(){
	var address1 = $.trim($("#store_area_list option:selected").val());
	var address2 = $.trim($("#store_sublist option:selected").val());
	if (address1 == "" || address2 == "") {
		alert("지역을 선택해주세요.");
	} else {
		server.stores(encodeURIComponent(address1), encodeURIComponent(address2), storesCallback);
	}
}

function storesCallback(response) {
    if (response.status === "OK") {
        displayStores(response.data);
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

function getAddress1() {
	server.address1(address1Callback);
	console.log("getAddress1");
}

function address1Callback(response) {
	if (response.status === "OK") {
		var $address1 = $("#store_area_list");
		var adds = response.data;
		for (var i = 0; i < adds.length; i++) {
			var html = '<option value="' + adds[i] + '">' + adds[i] + '</option>';
			$address1.append(html);
		}
	}
}

function getAddress2(address1) {
	server.address2(encodeURIComponent(address1), address2Callback);
}

function address2Callback(response) {
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

function sendSelectStoreSubmit(){
	var reqSeqNo = $.trim($("input[name=reqSeqNo]").val());
	var storeCd = $.trim($("input:radio[name=storeCd]:checked").val());
	var storeNm = $.trim($("input:radio[name=storeCd]:checked").data("store-name"));
	
	var storeAgree = $("input:checkbox[id='storeAgree']").is(":checked");
		
	// 입력값 체크
	if (reqSeqNo == ""){
		alert("예약주문번호가 없습니다.")
	} else if (storeCd == ""){
		alert("방문매장을 선택해주세요.")
	} else if(!storeAgree){
		alert("개인정보 처리 위탁 동의가 필요합니다.");
	} else {
		var message = storeNm + " 매장을 선택하셨습니다.\n"
				+ "방문매장 선택을 완료 하시겠습니까?";
		if (confirm(message) == true) {
			server.updateVisitStore(reqSeqNo, storeCd, updateVisitStoreCallback);
		} else {
			return;
		}
	}
}

function updateVisitStoreCallback(response) {

    if (response.status === "OK") {
        location.href = "/reserve5?reqSeqNo=" + response.data;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function changeSlideContents(course){
	var idx = sliderID + course;
	if (idx > -1 && idx < 3){
		
	}
	if (course < 0) phoneSlider.goToPrevSlide();
	else phoneSlider.goToNextSlide();
}

function changeReserveInfoPage(){
	$('.ownCertify').css('display', 'none');
	$('.reserveInfoList').css('display', 'block');
}

function setNextStep(step){
	$('.stepBlock').css('display', 'none');
	
	$('.stepBlock.step' + step).css('display', 'block');
	
	if (step == 2){
		sliderID = 0;
		
		$('.step2 input').val('');
		$('.step2 input').blur();
		
		setSelectPhoneColor(-1);
		
		$('.step_2_colorbg, .step_2_bottombg').css('display', 'block');
		
	} else{
		$('.step_2_colorbg, .step_2_bottombg').css('display', 'none');
	}
}

function setSelectPhoneColor(sdx){
	if (isColorChker){
		/*** 2017-02-25 ***/
		$('.colorSelectBtn').each(function(idx, item){
			var srcname = $(item).find('img').attr('src');
			if (idx == sdx) {
				srcname = srcname.replace("_off", "_on");
				$(this).attr('enabled', 'N')
			}else {
				srcname = srcname.replace("_on", "_off");
				$(this).attr('enabled', 'Y')
			}
			$(item).find('img').attr('src', srcname);
		});
		/***2017-02-25 ***/
		console.log(sdx)
		setPhoneSlider(sdx); /**2017-02-23 **/
		
		colorID = sdx;
	}
}

function setSelectPhoneColorM(sdx){
	if (modTypeID != 1){
		colorID = sdx;

		$('.btn_color_select li').each(function(idx, item){
			var enabled = idx == sdx ? "on" : "off";
			var imgname = "images/btn_color_" + (idx+1) + "_" + enabled + ".png";
			$(item).find('img').attr('src', imgname);		
		});
		
		setPhoneSliderM(sdx);
	}
}

function setSelectDevice(sdx){
	/*** 2017-02-25 ***/
	$('.deviceSelectBtn').each(function(idx, item){
		var srcname = $(item).find('img').attr('src');
		if (idx == sdx) {
			srcname = srcname.replace("_off", "_on");
			$(this).attr('enabled', 'N')
		}else {
			srcname = srcname.replace("_on", "_off");
			$(this).attr('enabled', 'Y')
		}
		$(item).find('img').attr('src', srcname);
	});
	/***2017-02-25 ***/

	isColorChker = true;
	
	/***2017-08-24 ***/	
	if (sdx == 1) setSelectPhoneColor(1);
	else setSelectPhoneColor(-1);
	
	modTypeID = sdx;
	
	isColorChker = modTypeID == 1 ? false : true;
}

function setSelectDeviceM(sdx){
	
	if (sdx == 1) setSelectPhoneColorM(1)
	
	modTypeID = sdx;
	
	$('.btn_product_select li').each(function(idx, item){
		var enabled = idx == sdx ? "on" : "off";
		var imgname = "images/btn_v30_" + (idx+1) + "_" + enabled + ".png";
		$(item).find('img').attr('src', imgname);		
	});
}


/***************** 2017-02-23 **********************************/
function setPhoneSlider(idx){
	
	if(idx < 0) idx = 0;
	
	if (phoneSlider != null){
		phoneSlider.destroySlider();
	}	

	$('.sliderImgs').empty();
	$('.sliderThums').empty();

	var elements = "";
	var thumelem = "";
	for(var i=0;i<3;i++){
		elements += '<div class="sliderImgBlock">';
		elements += '	<div class="sliderInnerBlock">';
		elements += '		<img src="images/phone_slider_' + (idx+1) + '_' + (i+1) + '.png"/>';
		elements += '	</div>';
		elements += '</div>';
		
		thumelem += '<img src="images/phone_slider_thum_' + (idx+1) + '_' + (i+1) + '.png"/>';
	}
	$('.sliderImgs').append(elements);
	$('.sliderThums').append(thumelem);
	
	$('.sliderThums img').each(function(idx, item){
		$(item).bind('click', function(){
			phoneSlider.goToSlide(idx);
		});
	});
	
	phoneSlider = $('.sliderImgs').bxSlider({
		controls : false,
		responsive : false,
		pager : false,
		slideWidth : 443,
	    minSlides: 1,
	    maxSlides: 1,
	    moveSlides : 1,
	    startSlide : 0,
		onSlideBefore : function(el){
			var matchID = phoneSlider.getCurrentSlide();
			$('.sliderPager img').each(function(idx, item){
				var srcname = $(item).attr('src');
				if (idx == matchID) var newsrc = srcname.replace("_off", "_on");
				else newsrc = srcname.replace("_on", "_off");
				$(item).attr('src', newsrc);
			});
		}
	});
}

function setQuestionOpen(odx){
	$('.questionElement').each(function(idx, item){
		if (odx == idx){
			$(item).find('.questionContent').css('display', 'block');
			var imgname = $(item).find('.tabArrow').attr('src').replace("_off", "_on");
			$(item).find('.tabArrow').attr('src', imgname);
		} else{
			$(item).find('.questionContent').css('display', 'none');
			setAnswerOpen(idx, -1);
			var imgname = $(item).find('.tabArrow').attr('src').replace("_on", "_off");
			$(item).find('.tabArrow').attr('src', imgname);
		}
	});
}

function setAnswerOpen(idx, cdx){
	$('.questionElement').eq(idx).find('.answerTxt').each(function(tdx, txt){
		if (cdx == tdx){
			var display = $(txt).css('display');
			if (display == "none") $(txt).css('display', 'block');
			else 	$(txt).css('display', 'none');
		} else{
			$(txt).css('display', 'none');
		}
	});
}
/***************** 2017-02-23 **********************************/

function changeSearchType(sdx){
	if (searchTypeID != sdx){
		$('.searchTypeBtn').each(function(idx, item){
			var srcname = $(item).find('img').attr('src');
			if (idx == sdx) srcname = srcname.replace("_off", "_on");
			else srcname = srcname.replace("_on", "_off");
			$(item).find('img').attr('src', srcname);
		});
		
		searchTypeID = sdx;
		
		$('.searchTypeFields').each(function(idx, item){
			if (idx == searchTypeID) $(item).css('display', 'block');
			else $(item).css('display', 'none');
		})
	}
}
/*** 체험매장 팝업 2017-02-26 ***/
function openSearchPopBlock(){
	$('#store_list').empty();
	$('#store_list').append('<div class="searchDefaultMsg">매장명 또는 지역별 방문 매장을 검색해 주세요.</div>');
	
	
	$('.inputBlock').val('');
	$('.inputBlock').blur();

	$("#store_area_list").empty();
	$("#store_area_list").append('<option value="" selected="selected">- 지역선택 -</option>');

	$("#store_sublist").empty();
	$("#store_sublist").append('<option value="" selected="selected">- 구/군 -</option>');
	
	changeSearchType(0);
	
	getAddress1();
	
	$('.storeSearchPop, .popBg').css('display', 'block');
	
	//alert('곧 만나보실 수 있습니다!');
}
function closeSearchPopBlock(){
	$('.storeSearchPop, .popBg').css('display', 'none');
}
/*** 체험매장 팝업 2017-02-26 ***/


function sendBenefitMorePage(idx){
	switch(idx){
	case 0:
		window.open('https://www.uplus.co.kr/ent/spps/chrg/RetrieveFamilyLove.hpi?mid=7471', '_blank');
		break;

	case 1:
		window.open('http://www.uplus.co.kr/ent/spps/chrg/RetrieveChrgDetailInfo.hpi?catgCd=51432&hposProdCd=C000014055', '_blank');
		break;

	case 2:
		window.open('https://www.uplus.co.kr/evt/mebf/megi/InitUbMbBenefit.hpi?mid=2219', '_blank');
		break;
		
	case 3:
		window.open('https://csp.uplusbox.co.kr/userGuide', '_blank');
		break;
	}
}

$(window).load(function(){
	$('.inputBlock').each(function(idx, item){		
		var plus = $(item).height() < 40 ? "" : "_2";
		var emptyimg = "images/" + $(item).attr('name') + plus + "_notify.gif";
		
		$(item).focus(function(){
			$(this).css({
				background:'#ffffff'
			});
		});

		$(item).blur(function(e){
			var value = $(this).val().replace( /(\s*)/g, "");
			if (value.length < 1)
			{
				$(this).val("");
				$(this).css({
					background : "#fff url('" + emptyimg + "') no-repeat"
				});
			}
		})
		
		$(item).blur();
	});
	/***************** 2017-02-23 **********************************/
	
	$('.questionElement').each(function(idx, item){
		$(item).find('.questionTab').bind('click', function(){
			setQuestionOpen(idx)
		});
		
		$(item).find('.answerTitle').each(function(cdx, child){
			$(child).bind('click', function(){
				setAnswerOpen(idx, cdx);
			});
		});
	});
	
	$('.selectViewer').each(function(idx, item){
		$(item).bind('click', function(){
			var display = $(this).parent().find('.selectList').css('display');
			if (display == "block") $(this).parent().find('.selectList').css('display', 'none');
			else $(this).parent().find('.selectList').css('display', 'block');
		});
		
		$(item).parent().find('.listItem').each(function(cdx, child){
			
			$(this).hover(function(){
				$(this).css('background', '#a7a7a7');
				var imgname = $(this).find('img').attr('src').replace("_off", "_on");
				$(this).find('img').attr('src', imgname);
			}, function(){
				$(this).css('background', '#ffffff');
				var imgname = $(this).find('img').attr('src').replace("_on", "_off");
				$(this).find('img').attr('src', imgname);
			});
			
			$(this).bind('click', function(){
				var tag = $(this).parent().parent().find('.selectViewer').find('img').eq(0);
				var imgname = $(this).find('img').attr('src').replace("_on", "_select");
				tag.attr('src', imgname);
				
				$(this).parent().parent().find('.selectList').css('display', 'none');
			});
		});
	});
	
	$('.topVisualBlock').css('display', 'block');
	$('.mainSlider').bxSlider({
		controls : false,
		auto:true,
		pause:2500 // 2017-02-24
	});
	
	setPhoneSlider(0);
	/***************** 2017-02-23 **********************************/

});

function sendOtp() {
	var name = $.trim($("input[name=username]").val());
	var phonNo = $.trim($("input[name=handphone]").val());
	
	var regExp = /^[0-9]+$/;
	
	// 입력값 체크
	if (name == ""){
		alert("이름을 입력해주세요.")
	} else if (phonNo == ""){
		alert("휴대폰번호를 입력해주세요.")
	} else if (!regExp.test(phonNo) || phonNo.length < 10){
		alert("정확한 번호를 입력해주세요.");
	} else {
		server.otp(phonNo, otpCallback);
	}
}

function otpCallback(response) {
	if (response.status === "OK") {
//        intervals.forEach(clearInterval);
		resetInterval();
        var authTime = 180;
        
        var i = setInterval(function() {
        	if (authTime != 0) {
        		authTime--;
        		var minutes = Math.floor(authTime / 60);
        		var seconds = authTime - (minutes * 60);
        		
        		if (minutes < 10) {minutes = "0"+minutes;}
        		if (seconds < 10) {seconds = "0"+seconds;}
        	    $("#timer").text("남은시간 "+minutes+":"+seconds);
        	} else {
        		alert("인증번호 입력시간이 초과되었습니다. 다시 버튼을 눌러주세요.")
        		$("#timer").text("");
//        		intervals.forEach(clearInterval);
        		resetInterval();
        	}
        }, 1000);
        
        intervals.push(i);
        
		alert("인증번호가 전송되었습니다. 3분 이내에 인증번호를 입력해주세요.");
	} else if (response.status === "ERROR") {
		alert(response.message);
		return;
	}
}


function sendAuth() {
	var phonNo = $.trim($("input[name=handphone]").val());
	var certNumber = $.trim($("input[name=certifynum]").val());
	
	var regExp = /^[0-9]+$/;
	
	// 입력값 체크
	if (certNumber == ""){
		alert("인증번호를 입력해주세요.")
	} else if (!regExp.test(certNumber)){
		alert("정확한 번호를 입력해주세요.");
	} else {
		server.certificate(phonNo, certNumber, certificateCallback);
	}
}

function certificateCallback(response) {
    if (response.status === "OK") {
        auth = true;
        $("#username").attr("readonly","readonly");
        $("#handphone").attr("readonly","readonly");
        $("#timer").text("");
//        intervals.forEach(clearInterval);
        resetInterval();
        alert("인증이 완료되었습니다.");
    } else if (response.status === "FAIL") {
    	alert("인증번호가 일치하지 않습니다.");
    	return;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function reserveConfirm() {
	var agree = $("input:checkbox[id='btnAgree']").is(":checked");
	
	// 입력값 체크
	if (!auth) {
		alert("본인인증이 필요합니다.");
	} else {
		$('#confirmForm').submit();
	}
}

function sendOrderCancel(object) {
	var reqSeqNo = $(object).data("seq");
	var message = "신청(예약번호:" + reqSeqNo + ")을 취소 하시겠습니까?";
	
	if (confirm(message) == true) {
		// 입력값 체크
		if (reqSeqNo == ""){
			alert("주문취소를 위한 값이 없습니다.");
		} else {
			var cancel = {
					reqSeqNo: reqSeqNo
			};
			
			server.cancelOrder(cancel, cancelOrderCallback);
		}
	} else {
		return;
	}
}

function cancelOrderCallback(response) {

    if (response.status === "OK") {
    	alert("신청취소가 정상적으로 처리되었습니다.");
    	$("#" + response.data).remove();
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function sendAceCount(str){
	_PL("http://v30.uplus.co.kr/" + str + ".html");
}

function FacebookPixelCode(seq){
    console.log(seq);

    switch (seq)
    {
		    case 0:
		    	fbq('track', 'ViewContent', {
		    		content_name: 'V30_사전예약마이크로사이트',
		    		value: 0.50,
		    		currency: 'USD'
		    		});
		break;
		    case 1:
		    	fbq('track', 'Lead', {
		    		  content_name: 'V30_사전예약바로가기',
		    		  content_category: '버튼클릭',
		    		  value: 0,
		    		  currency: 'USD'
		    		 }); 
		break;
        case 2:
        	fbq('track', 'Lead', {
        		  content_name: 'V30_사전예약신청',
        		  content_category: '버튼클릭',
        		  value: 0,
        		  currency: 'USD'
        		 });
          break;
        case 3:
        	fbq('track', 'Lead', {
        		  content_name: 'V30_유플러스만의구매혜택',
        		  content_category: '버튼클릭',
        		  value: 0,
        		  currency: 'USD'
        		 });  
        case 4:
        	fbq('track', 'Lead', {
        		  content_name: 'V30_유플러스만의구매혜택자세히보기',
        		  content_category: '버튼클릭',
        		  value: 0,
        		  currency: 'USD'
        		 });
       case 5:
    	   fbq('track', 'Lead', {
    		   content_name: 'V30_V30알아보기',
    		   content_category: '버튼클릭',
    		   value: 0,
    		   currency: 'USD'
    		  })
       case 6:
    	   fbq('track', 'Lead', {
    		   content_name: 'V30_매장방문사전예약',
    		   content_category: '버튼클릭',
    		   value: 0,
    		   currency: 'USD'
    		  }); 
          break;
       case 7:
    	   fbq('track', 'Lead', {
    		   content_name: 'V30_U+Shop사전예약',
    		   content_category: '버튼클릭',
    		   value: 0,
    		   currency: 'USD'
    		  });
          break;    
       case 8:
    	   fbq('track', 'ViewContent', {
    		   content_name: 'V30_사전예약신청접수완료',
    		   value: 0.50,
    		   currency: 'USD'
    		   });
    	   break;
       case 9:
    	   fbq('track', 'ViewContent', {
    		   content_name: 'V30_사전예약완료',
    		   value: 0.50,
    		   currency: 'USD'
    		   });
    	   break;
    }
   //alert("FacebookPixelCode : " + seq);
}

function resetInterval() {
    for(var i = 0, len = intervals.length; i < len; ++i) {
    	clearInterval(intervals[i]);
    }
}

function popOrgInfo(object) {
	var dlrNm = $(object).data("dlr-nm");
	var orgTelNo = $(object).data("org-tel-no");
	var orgAddress = $(object).data("org-address");
	/*
	alert("매장명 : " + dlrNm
//			+ "\n전화번호 : " + orgTelNo
			+ "\n주소 : " + orgAddress);
			*/
	
	drawMap(orgAddress, dlrNm);
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

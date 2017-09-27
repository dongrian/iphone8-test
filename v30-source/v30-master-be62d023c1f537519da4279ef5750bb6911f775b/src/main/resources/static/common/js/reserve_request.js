

var sliderID = 0;

var colorID = -1;

var searchTypeID = 0;

var phoneSlider;

function sendReserveUserInfo(){
	order();
}

function order() {
	var name = $("input[name=username]").val();
	var phonNo = $("input[name=handphone]").val();
	var giftCd = "Gift002";
	var colrCd = "CO005";
	if (colorID === 0) {
		colrCd = "CO005";
	} else if (colorID === 1) {
		colrCd = "CO006";
	} else if (colorID === 2) {
		colrCd = "CO007";
	} else if (colorID === 3) {
		colrCd = "CO008";
	}

    var order = {
		name: name,
		phonNo: phonNo,
		colrCd: colrCd,
		giftCd: giftCd
    };

    server.order(order, orderCallback);
}

function orderCallback(response) {

    if (response.status === "OK") {
        setStepData(response.data);
        location.href = "/reserve3?id" + response.data;
    } else if (response.status === "ERROR") {
        alert(response.message);
        return;
    }
}

function setStepData(data) {
	$(".reserveUname").text(data.name);
	$(".reserveInfoField.number").text(data.reqSeqNo);
	$(".reserveInfoField.model").text(getModelName(data.modTypeCd));
	$(".reserveInfoField.color").text(getColorName(data.colrCd));
	$(".reserveInfoField.name").text(getName(data.name));
	$(".reserveInfoField.phone").text(getPhoneName(data.phonNo));
	$(".reserveInfoField.gift").text(getGiftName(data.giftCd));

	setNextStep("2_1");
}

function getModelName(modTypeCd) {
	if (modTypeCd === "PH009") {
		return "LGM-G600L";
	}
}

function getColorName(colrCd) {
	if (colrCd === "CO005") {
		return "모로칸 블루";
	} else if (colrCd === "CO006") {
		return "오로라 블랙";
	} else if (colrCd === "CO007") {
		return "클라우드 실버";
	} else if (colrCd === "CO008") {
		return "라벤더";
	}
}

function getName(name) {
	return name;
}

function getPhoneName(phonNo) {
	return phonNo;
}

function getGiftName(giftCd) {
	return giftCd;
}

function sendStoreNameSearch(){
	alert("매장명검색")
}

function sendLocalNameSearch(){
	alert("지역명검색");
}

function sendSelectStoreSubmit(){
	setNextStep("3");
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

		phoneSlider = $('.sliderImgs').bxSlider({
			controls : false,
			responsive : false,
			pager : true,
			slideWidth : 440,
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

	} else {
		$('.step_2_colorbg, .step_2_bottombg').css('display', 'none');
	}
}

function setSelectPhoneColor(sdx){
	$('.colorSelectBtn').each(function(idx, item){
		var srcname = $(item).find('img').attr('src');
		if (idx == sdx) srcname = srcname.replace("_off", "_on");
		else srcname = srcname.replace("_on", "_off");
		$(item).find('img').attr('src', srcname);
	});

	colorID = sdx;
}

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



$(window).load(function(){
	$('.inputBlock').each(function(idx, item){
		var emptyimg = "images/" + $(item).attr('name') + "_notify.gif";

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
});

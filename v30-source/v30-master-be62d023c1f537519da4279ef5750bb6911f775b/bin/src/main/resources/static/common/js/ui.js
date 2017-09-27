//png24
function setPng24(obj){
	obj.width=obj.height=1;
	obj.className=obj.className.replace(/\bpng24\b/i,'');
	obj.style.filter ="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');"
	obj.src=''; 
	return '';
}


// 화면 정가운데 띄울 때 사용
function popHeightControl(divID,display){
	// black bg
	if(display=='block'){
		$('#bg_layer').css('height', $(document).height()).show();
		$('#' + divID)
		//.css('left', (($(window).width() / 2) - ($('#' + divID).width() / 2))) 수평, 가운데 일 경우
		.css('top', ($(window).scrollTop() + (($(window).height() / 2) - ($('#' + divID).height() / 2))))
		.show();
	}
	else if(display=='none'){
		$('#bg_layer').hide();
	}
	document.getElementById(divID).style.display=display;
}


//layer popup
function popControl(divID,display){

	// black bg
	if(display=='block'){
		document.getElementById('bg_layer').style.display = 'block';
	}
	else if(display=='none'){
		document.getElementById('bg_layer').style.display = 'none';
	}
	document.getElementById(divID).style.display=display;

}


/************* 2017-02-23 ******************************************/
function openQnaList(odx){
	$('.faq_list').each(function(idx, item){
		if (odx == idx) {
			$(item).find('dd').css('display', 'block');
			//$(item).find('dt').css('background', "#f4f4f4 url('images/bg_faq_on.jpg') no-repeat center top");
		}else {
			$(item).find('dd').css('display', 'none');
			//$(item).find('dt').css('background', "#f4f4f4 url('images/bg_faq_off.jpg') no-repeat center top");
			openQnaText(idx, -1);
		}
	});
}

function openQnaText(idx, cdx){
	$('.faq_list').eq(idx).find('dd ul li div').each(function(tdx, txt){
		if (cdx == tdx) {
			var display = $(txt).css('display');
			if (display == "none") $(txt).css('display', 'block');
			else $(txt).css('display', 'none');
		}else $(txt).css('display', 'none');
	});
}

function phoneColorSelectAct(idx){
	
}

var phoneSliderM;

function setPhoneSliderM(idx){
	if (phoneSliderM != null){
		phoneSliderM.destroySlider();
	}

	$('.bxslider').empty();
	$('.ico_paging').empty();
	
	var elements = "";
	var icoelements = "";
	for(var i=0;i<3;i++){
		elements += '<li><img src="images/slide/img_reserv_slide_' + (idx+1) + '_' + (i+1) + '.png" alt="" /></li>';
		
		icoelements += '<a href="javascript:changePhoneImg(' + i + ');"><img src="images/slide/m_ico_paging_' + (idx+1) + '_' + (i+1) + '.png" alt="" /></a>'
	}
	$('.bxslider').append(elements);
	$('.ico_paging').append(icoelements);
	
	phoneSliderM = $('.bxslider').bxSlider({
		speed: '500',
		auto : true,
		pager : false
	});
}

function changePhoneImg(idx){
	if (phoneSliderM != null) phoneSliderM.goToSlide(idx);
}

$(document).ready(function(){
	
	$('.btn_top_go').click(function(){
		$('html, body').animate({scrollTop:0}, 300, 'swing');
	});
	
	$('.faq_list').each(function(idx, item){
		$(item).find('dt').bind('click', function(){
			openQnaList(idx);
		});
		
		$(item).find('dd ul li span').each(function(cdx, child){
			$(child).bind('click', function(){
				openQnaText(idx, cdx);
			});
		});
	});
	openQnaList(0)
});

/************* 2017-02-23 ******************************************/
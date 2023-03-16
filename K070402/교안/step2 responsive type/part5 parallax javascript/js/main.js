window.addEventListener("load", function(){
	var n=0;
	var t=0;
	var targety=0;
	var scrollTimer=0;
	var winHalf;
	var top=document.getElementsByClassName("top")[0];
	var content=document.getElementsByClassName("content")[0];
	var contentLi=content.children;
	var gnbLi=gnb.firstElementChild.children;
	gnbLi[n].classList.add("active");

	function init(){
		winHalf=window.innerHeight/2;
	}
	init();

	window.addEventListener("resize", init);

	window.addEventListener("scroll", function(){
		clearTimeout(scrollTimer);

		scrollTimer=setTimeout(function(){
			t=window.pageYOffset;

			if(t < contentLi[1].offsetTop-winHalf){
				n=0;
			}
			else if(t < contentLi[2].offsetTop-winHalf){
				n=1;
			}
			else if(t < contentLi[3].offsetTop-winHalf){
				n=2;
			}
			else if(t < contentLi[4].offsetTop-winHalf){
				n=3;
			}
			else if(t < contentLi[5].offsetTop-winHalf){
				n=4;
			}
			else if(t < contentLi[6].offsetTop-winHalf){
				n=5;
			}
			else if(t < contentLi[7].offsetTop-winHalf){
				n=6;
			}
			else{
				n=7;
			}

			for(var i=0; i<gnbLi.length; i++){
				if(i == n){
					gnbLi[i].classList.add("active");
				}
				else{
					gnbLi[i].classList.remove("active");
				}
			}

			if(t > 80){
				if(top.classList.contains("fixed") == false){
					top.classList.add("fixed");
					gsap.fromTo(top, {top: -100}, {top: 0, duration: 0.3, delay: 0.5});
				}
			}
			else{
				if(top.classList.contains("fixed") == true){
					top.classList.remove("fixed");
					top.removeAttribute("style");
				}
			}
		}, 50);
	});

	for(var i=0; i<gnbLi.length; i++){
		gnbLi[i].index=i;

		gnbLi[i].addEventListener("click", function(e){
			e.preventDefault();
			n=e.currentTarget.index;
			targety=contentLi[n].offsetTop;
			gsap.to(window, {scrollTo: targety, duration: 0.5});
		});
	}
});
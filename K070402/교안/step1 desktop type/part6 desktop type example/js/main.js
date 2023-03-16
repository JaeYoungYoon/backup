/*
window.addEventListener("load", function(){
	var topBtn=document.getElementById("etc").firstElementChild;
	topBtn.style.display="block";

	topBtn.addEventListener("click", function(e){
		e.preventDefault();
		gsap.to(window, {scrollTo: 0, duration: 0.5});
	});
});
*/

window.addEventListener("load", function(){
	// 1) navigation
	var header=document.getElementById("header");

	for(var i=0; i<header.children.length; i++){
		if(header.children[i].className == "upper"){
			var upper=header.children[i];
		}
	}
	for(var i=0; i<upper.children.length; i++){
		if(upper.children[i].className == "nav"){
			var nav=upper.children[i];
		}
	}

	var navLi=nav.firstElementChild.children;

	for(var i=0; i<navLi.length; i++){
		navLi[i].addEventListener("mouseenter", function(e){
			e.target.classList.add("active");
		});
		navLi[i].addEventListener("mouseleave", function(e){
			e.target.classList.remove("active");
		});
		navLi[i].firstElementChild.addEventListener("focusin", function(e){
			e.target.parentElement.classList.add("active");
		});

		for(var j=0; j<navLi[i].children.length; j++){
			if(navLi[i].children[j].tagName == "UL"){
				var depth2Ul=navLi[i].children[j];

				for(var k=0; k<depth2Ul.children.length; k++){
					depth2Ul.children[k].index=k; // 0, 1, 2, 3

					depth2Ul.children[k].firstElementChild.addEventListener("focusout", function(e){
						var total=e.target.parentElement.parentElement.children.length;

						if(e.target.parentElement.index == total-1){
							e.target.parentElement.parentElement.parentElement.classList.remove("active");
						}
					});
				}
			}
		}
	}

	// 2) gallery
	var galleryN=0;
	var galleryTotal;

	var galleryArea=document.getElementsByClassName("keyvisual")[0];
	var [gallery, galleryControl]=galleryArea.children;
	var galleryLi=gallery.firstElementChild.children;
	galleryTotal=galleryLi.length;

	for(var i=0; i<galleryControl.children.length; i++){
		if(galleryControl.children[i].className == "num_control"){
			var control=galleryControl.children[i];
		}
	}

	var controlLi=control.firstElementChild.children;

	function setGallery(n){
		for(var i=0; i<galleryLi.length; i++){
			if(i == n){
				galleryLi[i].classList.add("active");
				controlLi[i].classList.add("active");
			}
			else{
				galleryLi[i].classList.remove("active");
				controlLi[i].classList.remove("active");
			}
		}
	}
	function intervalMoving(){
		if(galleryN < (galleryTotal-1)){ // 0, 1, 2, 3
			galleryN+=1;
		}
		else{
			galleryN=0;
		}
		setGallery(galleryN);
	}

	setGallery(galleryN);
	var id=this.setInterval(intervalMoving, 5000);

	for(var i=0; i<controlLi.length; i++){
		controlLi[i].index=i;

		controlLi[i].addEventListener("click", function(e){
			e.preventDefault();
			var index=e.currentTarget.index;

			if(galleryN != index){
				galleryN=index;
				setGallery(galleryN);
			}
		});
		controlLi[i].addEventListener("mouseenter", function(){
			clearInterval(id);
		});
		controlLi[i].addEventListener("mouseenter", function(){
			id=setInterval(intervalMoving, 5000);
		});
	}

	// 3) go top
	var t=0;
	var topBtn=document.getElementById("etc").firstElementChild;

	window.addEventListener("scroll", function(){
		t=window.pageYOffset;

		if(t > 100){
			if(topBtn.style.display != "block"){
				gsap.fromTo(topBtn, {display: "block", opacity: 0}, {opacity: 1, duration: 0.3});
			}
		}
		else{
			if(topBtn.style.display != "none"){
				gsap.to(topBtn, {opacity: 0, duration: 0.3, onComplete: function(){
					topBtn.style.display="none";
				}});
			}
		}
	});

	topBtn.addEventListener("click", function(e){
		e.preventDefault();
		gsap.to(window, {scrollTo: 0, duration: 0.5});
	});

	// 4) lnb
	var lnb=document.getElementsByClassName("lnb")[0];
	var lnbBtn=lnb.firstElementChild;

	lnbBtn.addEventListener("click", function(e){
		e.preventDefault();
		e.currentTarget.classList.toggle("active");
	});
});
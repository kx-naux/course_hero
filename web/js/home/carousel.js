var timeOut = 3000; //ms
var slideIndex = 1;
var autoOn = true;
update_idx(document.getElementsByClassName("pic-show").length);
autoSlides();

function update_idx(len) {
    document.getElementsByClassName("idx-show")[slideIndex - 1].innerHTML = slideIndex.toString() + "&nbsp;&nbsp;/&nbsp;&nbsp;" + (len).toString();
}

function autoSlides() {
    timeOut = timeOut - 20;
    if (autoOn == true && timeOut < 0) {
        slides();
    }
    setTimeout(autoSlides, 20);
}

function manual(n) {
    timeOut = 3000;
    var slides = document.getElementsByClassName("pic-show");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].className = dots[i].className.replace(" active-dot", "");
    }
    n < 0 ? slideIndex-- : slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    else if (slideIndex == 0) {
        slideIndex = 4;
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active-dot";
    update_idx(slides.length);
}

function show(n) {
    timeOut = 3000;
    var slides = document.getElementsByClassName("pic-show");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].className = dots[i].className.replace(" active-dot", "");
    }
    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    else if (slideIndex == 0) {
        slideIndex = 4
    }
    slides[n - 1].style.display = "block";
    dots[n - 1].className += " active-dot";
    update_idx(slides.length);
    slideIndex += 2;
}

function slides() {
    timeOut = 3000;
    var slides = document.getElementsByClassName("pic-show");
    var dots = document.getElementsByClassName("dot");

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].className = dots[i].className.replace(" active-dot", "");
    }
    slideIndex++;

    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active-dot";
    update_idx(slides.length);
}

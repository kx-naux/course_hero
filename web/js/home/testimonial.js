/*=============== SWIPER JS ===============*/
let swiperCards = new Swiper(".swiper", {
    loop: true,
    spaceBetween: 32,
    centeredSlides: true,

    mousewheel: {
        invert: false
    },

    autoplay: {
        delay: 5000, 
        disableOnInteraction: false
    },

    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
    },

    breakpoints: {
        660: {
            slidesPerView: 2
        },
        985: {
            slidesPerView: 3
        }
    }
});
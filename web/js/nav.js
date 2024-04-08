// Navbar onscroll and to top button
document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {
        if (window.scrollY > 10) {
            document.querySelector('.nav-bar').classList.add('nav-scroll');
        } else {
            document.querySelector('.nav-bar').classList.remove('nav-scroll');
        }
    });
});


// Navbar onscroll and to top button
document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {
        if (window.scrollY > 10) {
            document.querySelector('.nav-bar').classList.add('nav-scroll');
        } else {
            document.querySelector('.nav-bar').classList.remove('nav-scroll');
        }

        // Get the button:
        let topTopBtn = document.getElementById("toTopBtn");

        if (window.scrollY > 20) {
            topTopBtn.style.display = "block";
        } else {
            topTopBtn.style.display = "none";
        }
    });
});

// When the user clicks on the button, scroll to the top of the document
function toTopFunc() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

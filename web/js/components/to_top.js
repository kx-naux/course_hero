// When the user clicks on the button, scroll to the top of the document
function toTopFunc() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

// Navbar onscroll and to top button
document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {

        // Get the button:
        let topTopBtn = document.getElementById("toTopBtn");

        if (window.scrollY > 15) {
            topTopBtn.classList.add('active');
        } else {
            topTopBtn.classList.remove('active');
        }
    });
});
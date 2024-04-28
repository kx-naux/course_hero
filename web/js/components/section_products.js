//Move to product page onclick
function redirectToProductPage(product) {
    const id = product.getAttribute('courseID');
    window.location.href = "/course_hero/course?id=" + id;
}

//Add to cart / remove from cart
function cartButtonClick(evt) {
    // check is login?

    evt.preventDefault();
    const courseID = evt.target.closest('.course-product').getAttribute('courseID');
    console.log("Cart Clicked - Course ID:", courseID);
}

//Add to wishlist / remove from wishlist
function likeButtonClick(evt) {
    // check is login?

    evt.preventDefault();
    const courseID = evt.target.closest('.course-product').getAttribute('courseID');
    console.log("Like Clicked - Course ID:", courseID);
}

//Add scroll detect for the div
document.addEventListener("DOMContentLoaded", function () {
    const coursesDivs = document.querySelectorAll(".courses");
    const spdScrollLatest = 0.2 * window.screen.width;

    coursesDivs.forEach(coursesDiv => {
        coursesDiv.addEventListener("wheel", (evt) => {
            evt.preventDefault();
            coursesDiv.scrollBy({
                left: evt.deltaY < 0 ? -spdScrollLatest : spdScrollLatest
            });
        });
    });
});

//Move to product page onclick
function redirectToProductPage(product) {
    const id = product.getAttribute('courseID');
    window.location.href = "/course_hero/course?id=" + id;
}

//Add to cart / remove from cart
function cartButtonClick(evt) {
    evt.stopPropagation();

    const courseID = evt.target.closest('.course-product').getAttribute('courseID');
    const url = '/course_hero/update-cart';
    const data = {
        productID: courseID,
        action: "add"
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
            toast_msg(TOAST_ERROR,"Network Issue","Fail to add to cart");
        }
        return response.json();
    }).then(responseData => {
        console.log('Response from servlet:', responseData);
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Cart Clicked - Course ID:", courseID);
}

//Add to wishlist / remove from wishlist
function likeButtonClick(evt) {
    evt.stopPropagation();


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

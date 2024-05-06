// cart button on click
if (document.querySelector("button.course-cart") !== null) {
    document.querySelector("button.course-cart").addEventListener('click', () => {
        const id = document.querySelector("section.course-section").getAttribute("courseID");
        console.log(id);
    });
}

// wishlist button on click
if (document.querySelector("button.course-wish") !== null) {
    document.querySelector("button.course-wish").addEventListener('click', () => {
        const id = document.querySelector("section.course-section").getAttribute("courseID");
        console.log(id);
    });
}

// click and copy link to clipboard
document.querySelector("button.share-btn").addEventListener('click', () => {

    // Copy the text inside the text field
    navigator.clipboard.writeText(window.location.href);

    // Alert the copied text
    toast_msg(TOAST_DEFAULT, "", "Link copied");
});

const reviewSection = document.querySelector("section.all-review-section");

// show all reviews button
document.querySelector("button.all-review-btn").addEventListener('click', () => {
    // Toggle the 'active' class on the div
    if (reviewSection.classList.contains("active")) {
        reviewSection.classList.remove('active');
    } else {
        reviewSection.classList.add('active');
    }
});

// close button for all review section
document.querySelector("span.close-btn").addEventListener('click', () => {
    reviewSection.classList.remove('active');
});

// add review form validation check
document.getElementById("addReviewBtn").addEventListener("click", () => {
    const rating = document.querySelector('div.review-rating input[name="rate"]:checked');

    if (rating === null) {
        document.getElementById("reviewInvalidMsg").innerText = "Please select rate";
        return;
    }

    const addReviewForm = document.getElementById("addReviewForm");
    addReviewForm.submit();
});

// show / hide add review section
document.addEventListener("DOMContentLoaded", (event) => {
    const addReview = document.getElementById("addReviewStatus").value;
    var addReviewDiv = document.getElementById("addReviewDiv");

    if (addReview === "1") {
        addReviewDiv.style.display = "flex";
    } else {
        addReviewDiv.style.display = "none";
    }
});

// clear invalid msg after select rate
const rateInputs = document.querySelectorAll('div.review-rating input[name="rate"]');
rateInputs.forEach(input => {
    input.addEventListener("change", () => {
        document.getElementById("reviewInvalidMsg").innerText = "";
    });
});

// show more reviews button
document.querySelector("button.show-more-btn").addEventListener('click', () => {

    // fetch more comment

});
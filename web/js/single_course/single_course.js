// cart button on click
document.querySelector("button.course-cart").addEventListener('click', () => {
    const id = document.querySelector("section.course-section").getAttribute("courseID");
    console.log(id);
});

// wishlist button on click
document.querySelector("button.course-wish").addEventListener('click', () => {
    const id = document.querySelector("section.course-section").getAttribute("courseID");
    console.log(id);
});

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

// show more reviews button
document.querySelector("button.show-more-btn").addEventListener('click', () => {



});

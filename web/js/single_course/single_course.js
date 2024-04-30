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


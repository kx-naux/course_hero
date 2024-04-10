// dummy data
var courses = {
    'courses': [
        {
            "course_id": "2024563234234",
            "course_title": "The Ultimate Excel Programming Course",
            "course_img": "./img/course/beginner_excel.jpg",
            "author": ["Woo Yu Beng", "Snijders Wang Wang", "Low Kah Xuan"],
            "course_rating": 3.5,
            "course_number_rate": 2340,
            "course_tag": ["Hot Sell"],
            "course_price": 455.90,
            "course_normal_price": 655.50,
            "course_category": "Microsoft Excel",
            "course_label": ["9.5 Hour", "All Level"]
        },
    ]
}

//rendering the whole product section
function sectionProducts(title, url, course) {
    // Create element to contain the section
    const sectionDiv = document.createElement('section');

    // Return the section div
    return sectionDiv;
}

//render a product card
function productCard(course) {
    // Create a div element 
    const cardDiv = document.createElement('div');

    // Return the  div
    return cardDiv;
}

//Add scroll detect for the div
document.addEventListener("DOMContentLoaded", function() {
    const coursesDiv = document.querySelector(".courses");
    const spdScrollLatest = 0.6 * window.screen.width;

    coursesDiv.addEventListener("wheel", (evt) => {
        evt.preventDefault();
        coursesDiv.scrollBy({
            left: evt.deltaY < 0 ? -spdScrollLatest : spdScrollLatest
        });
    });
});

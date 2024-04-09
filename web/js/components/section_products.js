// dummy data
var courses = {
    'courses': [
        {
            "course_id": "",
            "course_title": "The Ultimate Excel Programming Course",
            "course_img": "./img/course/beginner_excel.jpg",
            "author": "Woo Yu Beng",
            "course_rating": 3.5,
            "course_number_rate": 2340,
            "course_tag": ["Hot Sell"],
            "course_price": 455.90,
            "course_normal_price": 655.50,
            "course_category" : "Microsoft Excel",
            "course_update_date" : "8/4/2024",
            "course_label" : ["9.5 Hour","All Level"],
            "course_desc" : "This is description of the course",
            "course_feature" : ["Feature 1","Feature 2","Feature 3"]
        },
    ]
}


function sectionProducts(title, url, course) {
    // Create element to contain the section
    const sectionDiv = document.createElement('section');

    // Return the section div
    return sectionDiv;
}

function productCard(course) {
    // Create a div element 
    const cardDiv = document.createElement('div');

    // Return the  div
    return cardDiv;
}
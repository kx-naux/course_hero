// add event listener to handle the item in pic
const itemLabels = document.querySelectorAll("div.merch-pic-info");
itemLabels.forEach(item => {
    item.addEventListener('click', (event) => {

        const id = item.getAttribute("merchID");
        window.location.href = "/course_hero/merch?id=" + id;

    });
});

//add event handler for the mearch item
const merchItems = document.querySelectorAll("div.merch-list-item");
merchItems.forEach(item => {
    item.addEventListener('click', (event) => {

        const id = item.getAttribute("merchID");
        window.location.href = "/course_hero/merch?id=" + id;

    });
});

// toggle the accordion

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
const merchCategories = document.querySelectorAll("div.merch-category");
merchCategories.forEach(cat => {
    cat.addEventListener('click', () => {
        var list = cat.querySelector("div.merch-category-item-list");
        var icon = cat.querySelector("div.merch-category-title-div i");
        icon.classList = "";

        if (list.classList.contains("active")) {
            list.classList.remove("active");
            icon.classList.add("ri-add-fill");
        } else {
            list.classList.add("active");
            icon.classList.add("ri-subtract-fill");
        }
    });
});

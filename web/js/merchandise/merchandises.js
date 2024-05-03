// add event listener to handle the item in pic
const itemLabels = document.querySelectorAll("div.merch-pic-info");
itemLabels.forEach(item => {
    item.addEventListener('click', (event) => {

        const id = item.getAttribute("merchID");
        window.location.href = "/course_hero/merch?id=" + id;

    });
});


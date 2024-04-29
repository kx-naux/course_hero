// Get all elements with the class "filter-div"
const filterDivs = document.querySelectorAll("div.filter-type-title");

// Loop through each element and add the event listener
filterDivs.forEach(divElement => {
    divElement.addEventListener('click', (event) => {
        
        // Target the parent div element with class "filter-div"
        const divElement = event.target.parentElement;
        
        // Toggle the 'active' class on the div
        if (divElement.classList.contains("active")) {
            divElement.classList.remove('active');
        } else {
            divElement.classList.add('active');
        }
    });
});

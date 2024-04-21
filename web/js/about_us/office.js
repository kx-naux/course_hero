function changeOffice(e) {
    const offices = document.querySelectorAll(".office");
    const map = document.querySelector(".office-emb-map");
    const address = document.querySelector(".office-address");

    // Remove selected class for all office divs
    offices.forEach(office => {
        office.classList.remove("selected");
        const img = office.querySelector("img");
        if (img && img.src.includes("_slc.png")) {
            img.src = img.src.replace("_slc.png", ".png");
        }
    });

    // add selected class
    e.classList.add("selected");

    // Change the image by adding _slc before .png
    const img = e.querySelector("img");
    if (img && !img.src.includes("_slc.png")) {
        img.src = img.src.replace(".png", "_slc.png");
    }

    // Change iframe src and address text
    map.setAttribute("src", e.querySelector(".office-map").innerText);
    address.innerHTML = e.querySelector(".office-address").innerHTML;
}



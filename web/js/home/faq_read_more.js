function faqShow(button) {
    const faqDiv = document.querySelector("div.faq-div-content");
    var status = button.getAttribute('status');

    if (status === '0') {
        button.textContent = 'Show Less';
        button.setAttribute('status', '1');
        faqDiv.classList.add('active');
    } else {
        button.textContent = 'Show More';
        button.setAttribute('status', '0');
        faqDiv.classList.remove('active');
    }
}

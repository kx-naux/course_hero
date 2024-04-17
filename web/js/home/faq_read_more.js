function faqShow(button) {
    const faqDiv = document.querySelector("div.faq-div-content");
    var status = button.getAttribute('status');

    if (status === '0') {
        button.textContent = 'Show less';
        button.setAttribute('status', '1');
        faqDiv.classList.add('active');
    } else {
        button.textContent = 'Show more';
        button.setAttribute('status', '0');
        faqDiv.classList.remove('active');
    }
}

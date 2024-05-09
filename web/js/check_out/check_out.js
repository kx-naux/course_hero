// submit form when need refresh
function submitForm(action) {
    const form = document.getElementById("checkOutForm");
    const formAction = document.getElementById("fromAction");

    formAction.value = action;
    form.submit();
}

// listener for shippiing method
const shippingRadios = document.querySelectorAll('div.shipping-method');

shippingRadios.forEach(shippingMethod => {
    shippingMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        submitForm("shipping method change");

    });
});

// listener for address
if (document.querySelector(".stored-address-div") !== null) {
    const addressRadios = document.querySelectorAll('div.address-div');

    addressRadios.forEach(addressDiv => {
        addressDiv.addEventListener('click', function () {
            // Find the radio button inside the clicked shipping method div
            const radioButton = this.querySelector('input[type="radio"]');
            radioButton.checked = true;

        });
    });
}

// listener for address format
const postalCodeField = document.querySelector("input.postal-code");
if (postalCodeField !== null) {
    postalCodeField.addEventListener('input', (event) => {
        const inputValue = event.target.value;
        const regex = /^\d*$/; // Regular expression to allow only digits

        if (!regex.test(inputValue)) {
            event.target.value = inputValue.replace(/\D/g, ''); // Remove any non-digit characters
        }
    });
}

// listener for promo code format
const promoField = document.getElementById("promo");
if (promoField !== null) {
    promoField.addEventListener("input", (event) => {
        let inputValue = event.target.value;
        const regex = /^[a-zA-Z0-9]*$/;
        if (!regex.test(inputValue)) {
            inputValue = inputValue.replace(/[^a-zA-Z0-9]/g, '');
        }
        event.target.value = inputValue.toUpperCase();
    });
}

// show payment input div if checked
document.addEventListener("DOMContentLoaded", () => {
    showPaymentInputDiv();
});

// listener for payment method
const paymentRadios = document.querySelectorAll('div.payment-method');
paymentRadios.forEach(paymentMethod => {
    paymentMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        showPaymentInputDiv();

    });
});

function showPaymentInputDiv() {
    const paymentRadios = document.querySelectorAll('div.payment-method');
    const paymentInputDiv = document.querySelectorAll('div.payment-method-input');

    paymentInputDiv.forEach(inputDiv => {
        inputDiv.style.display = "none";
    });

    let selectedIndex = -1; // Default value if no radio button is checked

    paymentRadios.forEach((paymentMethod, index) => {
        const radio = paymentMethod.querySelector('input[type="radio"]');
        if (radio.checked) {
            selectedIndex = index;
        }
    });
    
    paymentInputDiv[selectedIndex].style.display = "flex";
    
    let checkbox = paymentInputDiv[selectedIndex].querySelector("input[type='checkbox']");
    if (checkbox !== null) {
        checkbox.checked = false;
    }
    
    document.getElementById("storingPayment").checked = false;
}

// form submission
document.getElementById("placeOrderBtn").addEventListener("click", () => {
    submitCheckoutForm();
});

function submitCheckoutForm() {

    const form = document.getElementById("checkOutForm");
    form.submit();
}


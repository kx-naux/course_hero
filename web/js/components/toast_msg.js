const TOAST_ERROR = 1;
const TOAST_SUCCESS = 2;
const TOAST_WARNING = 3;
const TOAST_DEFAULT = 4;

let timeoutId; // Declare a variable to hold the timeout ID globally
const toast = document.querySelector(".toast");
const toast_progess = toast.querySelector(".toast-progress");
const toast_close = toast.querySelector(".toast-close");
const toast_icon = toast.querySelector(".toast-type");
const toast_title = toast.querySelector(".toast-text-1");
const toast_message = toast.querySelector(".toast-text-2");

/**
 * Displays a toast message with the specified type, title, and message.
 * 
 * @param {number} type - The type of toast message. Use one of the predefined constants: TOAST_ERROR, TOAST_SUCCESS, TOAST_WARNING, or TOAST_DEFAULT.
 * @param {string} title - The title of the toast message.
 * @param {string} message - The content of the toast message.
 */
function toast_msg(type = TOAST_DEFAULT, title, message) {
    toast_title.innerText = title;
    toast_message.innerText = message;
    
    clearTimeout(timeoutId); // Clear previous timeout
    toast_progess.classList.remove('active');
    toast_progess.classList.add("reset-animation");

    toast.className = 'toast';
    toast.classList.add("active");

    toast_icon.className = 'toast-type';

    if (type === TOAST_ERROR) {
        toast.classList.add("toast-error");
        toast_icon.classList.add("ri-close-line");
    } else if (type === TOAST_SUCCESS) {
        toast.classList.add("toast-success");
        toast_icon.classList.add("ri-check-line");
    } else if (type === TOAST_WARNING) {
        toast.classList.add("toast-warning");
        toast_icon.classList.add("ri-alert-line");
    } else {
        toast_icon.classList.add("ri-information-line");
    }

    void toast_progess.offsetWidth; // Trigger reflow to apply CSS changes immediately
    toast_progess.classList.remove("reset-animation");
    toast_progess.classList.add("active");

    timeoutId = setTimeout(() => {
        toast.className = 'toast';
    }, 5000);
}

//close button
toast_close.addEventListener("click", () => {
    toast.className = 'toast';
});
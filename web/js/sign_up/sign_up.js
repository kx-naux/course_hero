const signUpPages = document.querySelectorAll("div.sign-up-p");
const formProgress = document.querySelector("input#formProgress");
const progressPoints = document.querySelectorAll("div.progress-point");
const progressLines = document.querySelectorAll("hr.progress-line");
const nextBtns = document.querySelectorAll("input.next-btn");
const backBtns = document.querySelectorAll("input.back-btn");

function showSignUpPage(n) {

    formProgress.value = n;

    // Update progress points
    progressPoints.forEach((point, i) => {
        point.classList.remove("complete", "current");
        if (i + 1 < n) {
            point.classList.add("complete");
            point.querySelector("span").innerText = "✔";
        } else if (i + 1 == n) {
            point.classList.add("current");
            point.querySelector("span").innerText = i + 1;
        } else {
            point.querySelector("span").innerText = i + 1;
        }
    });

    // Update progress lines
    progressLines.forEach((line, i) => {
        line.classList.remove("complete");
        if (i + 1 < n) {
            line.classList.add("complete");
        }
    });

}

// document on load update the progress bar
document.addEventListener("DOMContentLoaded", (event) => {
    showSignUpPage(formProgress.value);
});

// next button event handler
nextBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        showSignUpPage(parseInt(formProgress.value) + 1);
    });
});

// back button event handler
backBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        showSignUpPage(parseInt(formProgress.value) - 1);
    });
});

// on submit check 



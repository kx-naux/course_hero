//phone menu usage only
//const sideMenu = document.querySelector("aside");
//const menuBtn = document.querySelector("#menu-btn");
//const closeBtn = document.querySelector("#close-btn");

//theme changer
const themeToggler = document.querySelector(".theme-toggler");

/*
//show sidebar
menuBtn.addEventListner('click', ()=>{
    sideMenu.style.display = 'block';
})

//close sidebar
closeBtn.addEventListner('click', () => {
    sideMenu.style.display = 'none';
})
 */

//theme changer
themeToggler.addEventListener('click', () => {
    const currentValue = document.documentElement.style.getPropertyValue('--color-background');
    if(currentValue === '#181a1e'){
        document.documentElement.style.setProperty('--color-background', '#f6f6f9');
        document.documentElement.style.setProperty('--color-white', '#fff');
        document.documentElement.style.setProperty('--color-dark', '#363949');
        document.documentElement.style.setProperty('--color-dark-variant', '#677483');
        document.documentElement.style.setProperty('--color-light', 'rgba(132, 139, 200, 0.18)'); 
    }
    else{
        document.documentElement.style.setProperty('--color-background', '#181a1e');
        document.documentElement.style.setProperty('--color-white', '#20528');
        document.documentElement.style.setProperty('--color-dark', '#edeffd');
        document.documentElement.style.setProperty('--color-dark-variant', '#a3bdcc');
        document.documentElement.style.setProperty('--color-light', 'rgba(0,0,0,0.4)'); 
    }
    themeToggler.querySelector('i:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('i:nth-child(2)').classList.toggle('active');
}); 
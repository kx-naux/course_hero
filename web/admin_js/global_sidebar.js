let btn = document.querySelector('.expand-btn');
let sidebar = document.querySelector('.global-sidebar');

btn.onclick = function(){
    sidebar.classList.toggle('active');
};
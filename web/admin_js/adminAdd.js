let createBtn = document.querySelector('.submit-container.update .submit-button');

createBtn.onclick = function(e){
  Swal.fire({
  title: "Record Created",
  text: "Your work has been saved!",
  icon: "success"
  });
};
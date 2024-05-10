let checkedAllBoxHead = document.querySelector("thead .js-check-all");
let checkedAllBoxFoot = document.querySelector("tfoot .js-check-all");
let checkBoxes = document.querySelectorAll('.custom-table tbody input[type=checkbox]');
let tableRows = document.querySelectorAll('.custom-table tbody tr');

//check all Boxes Head
checkedAllBoxHead.onclick = function () {
    if (checkedAllBoxHead.checked) {
        checkBoxes.forEach(checkBox => {
            checkBox.checked = true;
        });
        addActiveClassToRow();
        checkedAllBoxFoot.checked = true;
    } else {
        checkBoxes.forEach(checkBox => {
            checkBox.checked = false;
        });
        removeActiveClassToRow();
        checkedAllBoxFoot.checked = false;
    }
};

//check all Boxes Foot
/*checkedAllBoxFoot.onclick = function () {
    if (checkedAllBoxFoot.checked) {
        checkBoxes.forEach(checkBox => {
            checkBox.checked = true;
        });
        addActiveClassToRow();
        checkedAllBoxHead.checked = true;
    } else {
        checkBoxes.forEach(checkBox => {
            checkBox.checked = false;
        });
        removeActiveClassToRow();
        checkedAllBoxHead.checked = false;
    }
};*/



for (let i = 0; i < checkBoxes.length; i++) {
  checkBoxes[i].onclick = function(){
    addActiveClassToRow();
    removeActiveClassToRow();
  };    
}

function addActiveClassToRow (){
  for (let i = 0; i < tableRows.length; i++) {
    if(checkBoxes[i].checked){
      tableRows[i].classList.add('active');
    }
  }
}

function removeActiveClassToRow(){
  for (let i = 0; i < tableRows.length; i++) {
    if(!(checkBoxes[i].checked)){
      tableRows[i].classList.remove('active');
    }
  }
}

// Toggle Edit Function for Table
let editBtns = document.querySelectorAll(".custom-table tbody tr .row-actions-edit");
let editCancelBtns = document.querySelectorAll(".custom-table tbody tr .edit-items-container .cancel input");

for(var i = 0; i < editBtns.length; i++){
    editBtns[i].setAttribute("editIndex", i.toString());
    editBtns[i].onclick = function(e){
        e.preventDefault();
        let index = e.target.getAttribute("editIndex");
        tableRows[index].classList.toggle("edit-status");
    };
}

for(var i = 0; i < editCancelBtns.length; i++){
    editCancelBtns[i].setAttribute("editIndex", i.toString());
    editCancelBtns[i].onclick = function (e) {
        e.preventDefault();
        let index = e.target.getAttribute("editIndex");
        tableRows[index].classList.toggle("edit-status");
    };
}

// Delete Btn Confirmation for Table
let deleteBtns = document.querySelectorAll(".custom-table tbody tr .row-actions-delete");

for(var i = 0; i < deleteBtns.length; i++){
    deleteBtns[i].onclick = function(e){
        e.preventDefault();
        Swal.fire({
            title: "Delete Confirmation",
            text: "You won't be able to revert this process!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#7380ec",
            cancelButtonColor: "#ff7782",
            confirmButtonText: "Yes, Delete"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Deleted!",
                    text: "Record has been deleted.",
                    icon: "success"
                });
            }
        });
    };
}

var DELETE_ROOM_FORM = document.querySelector('#deleteRoomForm');
var EDIT_ROOM_FORM = document.querySelector('#editRoomForm');
DELETE_ROOM_FORM.addEventListener('submit',deleteRoom);
EDIT_ROOM_FORM.addEventListener('submit',editRoom);
function deleteRoom(){
  alert('Удалить комнату?');
}
function editRoom(){
alert('Отредактировать комнату?')
}
function getValue() {

    // textarea 값 읽어오기 
    const user_idValue = document.getElementById('user_id').value;
    const adventuretextValue = document.getElementById('adventure').value; 
    const short_description = document.getElementById('short_description').value;
    const textareaValue = document.getElementById('textarea').value;
    const file = document.querySelector('input[type=file]').files;
    const local_list = document.querySelector('input[name="local"]:checked').value;
    const category = document.getElementsByName("category");
    const mbti =document.getElementsByName("mbti");
           
    console.log(user_idValue)
    console.log(adventuretextValue)
    console.log(short_description)
    console.log(textareaValue)
    console.log(local_list)
    console.log(file)
    for (var i = 0; i < category.length; i++) {
        if (category[i].checked) {
             console.log(category[i].value);
        }
    }
    for (var i = 0; i < mbti.length; i++) {
        if (mbti[i].checked) {
             console.log(mbti[i].value);
        }
    }
       
}
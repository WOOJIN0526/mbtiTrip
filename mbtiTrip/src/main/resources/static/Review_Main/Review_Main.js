document.querySelector('#heart').addEventListener('click', function(e){
    if(e.target.classList.contains('fa-heart-o')){
        e.target.classList.remove('fa-heart-o');
        e.target.classList.add('fa-heart');
    }else if(e.target.classList.contains('fa-heart')){
        e.target.classList.remove('fa-heart');
        e.target.classList.add('fa-heart-o');
    }
});
const stars =document.querySelectorAll('.Review_header_star i');

function toggleStar(SIndex){
    if(stars[SIndex].classList.contains('fas')){
        stars.forEach(function(star){
            star.classList.remove('fas'); 
            star.classList.add('far');
        });
        stars.forEach(function(star,index){
            if(index<=SIndex){
                star.classList.remove('fas');
                star.classList.add('far');
            }
        });
    }else if(stars[SIndex].classList.contains('far')){
        stars.forEach(function(star){
            star.classList.remove('fas'); 
            star.classList.add('far');
        });
        stars.forEach(function(star,index){
            if(index<=SIndex){
                star.classList.remove('far');
                star.classList.add('fas');
            }
        });
    }
}
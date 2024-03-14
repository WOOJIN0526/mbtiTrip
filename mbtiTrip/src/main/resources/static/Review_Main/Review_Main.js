document.querySelector('.icon-menu').addEventListener('click', function(){
    document.querySelector('.profile').classList.toggle('disActive');
    document.querySelector('.icon-menu').classList.toggle('animate')
});
console.log(document.querySelector('#heart'));
document.querySelector('#heart').addEventListener('click', function(e){
    if(e.target.classList.contains('fa-heart-o')){
        e.target.classList.remove('fa-heart-o');
        e.target.classList.add('fa-heart');
    }else if(e.target.classList.contains('fa-heart')){
        e.target.classList.remove('fa-heart');
        e.target.classList.add('fa-heart-o');
    }
});

// const images = document.querySelectorAll('.image-container .image');
// const rectangles = document.querySelectorAll('.pagination .rectangle');

// images.forEach((image, index) => {
//     image.addEventListener('transitionend', () => {
//         rectangles.forEach((rectangle, rectangleIndex) => {
//             rectangle.classList.remove('active');
//             if (index === rectangleIndex) {
//                 rectangle.classList.add('active');
//             }
//         });
//     });
// });
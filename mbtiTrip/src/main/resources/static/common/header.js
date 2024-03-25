document.querySelector('.icon-menu').addEventListener('click', function(){
    document.querySelector('.profile').classList.toggle('disActive');
    document.querySelector('.icon-menu').classList.toggle('animate')
});
console.log(document.querySelector('#heart'));


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